package com.ejercicio.once.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ejercicio.once.R;
import com.ejercicio.once.controller.EstudianteController;

public class AgregarEstudianteActivity extends AppCompatActivity {

    private EditText etNombre;
    private Button btnGuardar;
    private EstudianteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_estudiante);

        controller = new EstudianteController(this);
        etNombre = findViewById(R.id.etNombre);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            if (!nombre.isEmpty()) {
                controller.insertar(nombre);
                finish();
            } else {
                etNombre.setError("El nombre no puede estar vacío");
            }
        });
    }
}