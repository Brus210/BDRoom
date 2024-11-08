package com.example.basededatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basededatos.database.AppDatabase;
import com.example.basededatos.database.ExercisesDAO;
import com.example.basededatos.model.Exercises;
import com.example.basededatos.repository.ExercisesRepository;
import com.example.basededatos.viewmodel.ListaExercisesViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText txtRegName, txtRegMuscle, txtRegDesc;
    private Button btnAgregar, btnSiguiente;
    private AppDatabase db;
    private ExercisesRepository exercisesRepository;
    private Executor executor = Executors.newSingleThreadExecutor(); // Declara el Executor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db =AppDatabase.getInstance(this.getApplicationContext());
        ExercisesDAO exercisesDAO = db.exercisesDAO();
        exercisesRepository = new ExercisesRepository(exercisesDAO);

        btnSiguiente =(Button) findViewById(R.id.btnSiguiente);
        btnAgregar =(Button) findViewById(R.id.btnAgregar);

        txtRegName = (EditText) findViewById(R.id.txtRegName);
        txtRegMuscle = (EditText) findViewById(R.id.txtRegMuscle);
        txtRegDesc = (EditText) findViewById(R.id.txtRegDesc);


        btnSiguiente.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListaRecycler.class);
            startActivity(intent);
        });

        btnAgregar.setOnClickListener(view -> {
            String name = txtRegName.getText().toString();
            String muscle = txtRegMuscle.getText().toString();
            String desc = txtRegDesc.getText().toString();

            // Verifica que los campos no estén vacíos
            if (name.isEmpty() || muscle.isEmpty() || desc.isEmpty()) {
                Toast.makeText(MainActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            Exercises exercises = new Exercises();
            exercises.setName(name);
            exercises.setMuscle(muscle);
            exercises.setDescription(desc);

            //inserta en la base de datos
            executor.execute(() -> {
                exercisesRepository.insert(exercises);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Ejercicio agregado", Toast.LENGTH_SHORT).show();
                });
            });

            //clear fields
            txtRegName.setText("");
            txtRegMuscle.setText("");
            txtRegDesc.setText("");

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}