package com.example.basededatos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatos.adapter.ExercisesListAdapter;
import com.example.basededatos.database.AppDatabase;
import com.example.basededatos.database.ExercisesDAO;
import com.example.basededatos.model.Exercises;
import com.example.basededatos.repository.ExercisesRepository;
import com.example.basededatos.viewmodel.ListaExercisesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaRecycler extends AppCompatActivity
                    implements ExercisesListAdapter.ElementClickListener {

    private RecyclerView recyclerView;
    private ExercisesListAdapter exercisesListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ListaExercisesViewModel listaExercisesViewModel;
    private List<Exercises> exercises = new ArrayList<>();  // Inicializa la lista vacía;
    private AppDatabase db;
    private ExercisesRepository exercisesRepository;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recycler);


        db=AppDatabase.getInstance(this.getApplicationContext());
        ExercisesDAO exercisesDAO = db.exercisesDAO();
        exercisesRepository = new ExercisesRepository(exercisesDAO);

        recyclerView = findViewById(R.id.recyclerView); // Initialize recyclerView
        linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);




        listaExercisesViewModel = new ViewModelProvider(this).get(ListaExercisesViewModel.class);

        // Execute the load in the background
        executorService.execute(() -> {
            List<Exercises> exercisesList = exercisesRepository.getAll();
            runOnUiThread(() -> {
                exercises = exercisesList;
                exercisesListAdapter.setExercisesList(exercises);
            });
        });


        exercisesListAdapter = new ExercisesListAdapter(this, exercises, this);
        recyclerView.setAdapter(exercisesListAdapter);

        final Observer<List<Exercises>> observer = new Observer<List<Exercises>>() {
            @Override
            public void onChanged(List<Exercises> exercisesList) {
                if(exercisesList !=null){
                    exercises = exercisesList;
                    exercisesListAdapter.setExercisesList(exercisesList);
                }
            }
        };
        listaExercisesViewModel.getLista().observe(this, observer);
    }



    @Override
    public void onElementClick(Exercises exercises) {

    }

    @Override
    public void onBtnElementClick(Exercises exercises) {
    Intent intent = new Intent(ListaRecycler.this, UpdDelActivity.class);
    intent.putExtra("exercise", exercises);
    startActivity(intent);
    }
}