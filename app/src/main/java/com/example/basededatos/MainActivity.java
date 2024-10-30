package com.example.basededatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basededatos.database.AppDatabase;
import com.example.basededatos.database.ExercisesDAO;
import com.example.basededatos.model.Exercises;
import com.example.basededatos.repository.ExercisesRepository;
import com.example.basededatos.viewmodel.ListaExercisesViewModel;

public class MainActivity extends AppCompatActivity {
    private EditText txtRegName, txtRegMuscle, txtRegDesc;
    private Button btnAgregar, btnSiguiente;
    private AppDatabase db;
    private ListaExercisesViewModel exercisesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        db =AppDatabase.getInstance(this.getApplicationContext());
        ExercisesDAO exercisesDAO = db.exercisesDAO();
        ExercisesRepository exercisesRepository = new ExercisesRepository(exercisesDAO);
        Exercises exercises = new Exercises();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSiguiente =(Button) findViewById(R.id.btnSiguiente);

        btnAgregar =(Button) findViewById(R.id.btnAgregar);
        txtRegName = (EditText) findViewById(R.id.txtRegName);
        txtRegMuscle = (EditText) findViewById(R.id.txtRegMuscle);
        txtRegDesc = (EditText) findViewById(R.id.txtRegDesc);

        btnSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ListaRecycler.class);
                startActivity(intent);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                exercises.setNombre(txtRegName.getText().toString());
                exercises.setMuscle(txtRegMuscle.getText().toString());
                exercises.setDescription(txtRegDesc.getText().toString());
                exercisesRepository.insert(exercises);
                Toast.makeText(MainActivity.this, "Ejercicio agregado", Toast.LENGTH_SHORT).show();
            }
        });


    }
}