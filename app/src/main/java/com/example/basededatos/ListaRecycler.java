package com.example.basededatos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatos.adapter.ExercisesListAdapter;
import com.example.basededatos.database.AppDatabase;
import com.example.basededatos.database.ExercisesDAO;
import com.example.basededatos.model.Exercises;
import com.example.basededatos.repository.ExercisesRepository;
import com.example.basededatos.viewmodel.ListaExercisesViewModel;

import java.util.List;

public class ListaRecycler extends AppCompatActivity
                    implements ExercisesListAdapter.ElementClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ExercisesListAdapter exercisesListAdapter;
    private ListaExercisesViewModel listaExercisesViewModel;
    private List<Exercises> exercises;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recycler);
        recyclerView.setLayoutManager((linearLayoutManager));

        db=AppDatabase.getInstance(this.getApplicationContext());
        ExercisesDAO exercisesDAO = db.exercisesDAO();
        ExercisesRepository exercisesRepository = new ExercisesRepository(exercisesDAO);
        //
        listaExercisesViewModel = new ViewModelProvider(this).get(ListaExercisesViewModel.class);
        listaExercisesViewModel.setExercises(exercisesRepository.getAll());

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



        //INICIO
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //FIN
    }



    @Override
    public void onElementClick(Exercises exercises) {

    }

    @Override
    public void onBtnElementClick(Exercises exercises) {
    Intent intent = new Intent(ListActivity.this, UpdDelActivity.class);
    Intent.putExtra("exercise", exercises);
    startActivity(intent);
    }
}