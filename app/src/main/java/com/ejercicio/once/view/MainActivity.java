package com.ejercicio.once.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejercicio.once.R;
import com.ejercicio.once.adapter.EstudianteAdapter;
import com.ejercicio.once.controller.EstudianteController;
import com.ejercicio.once.model.Estudiante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EstudianteController controller;
    private EstudianteAdapter adapter;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new EstudianteController(this);
        recyclerView = findViewById(R.id.recyclerEstudiantes);
        fabAgregar = findViewById(R.id.fabAgregar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarEstudianteActivity.class);
            startActivity(intent);
        });

        cargarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    private void cargarLista() {
        List<Estudiante> lista = controller.obtenerTodos();
        adapter = new EstudianteAdapter(lista, estudiante -> {
            Intent intent = new Intent(this, DetalleEstudianteActivity.class);
            intent.putExtra("idEstudiante", estudiante.getId());
            startActivity(intent);
        }, estudiante -> {
            controller.eliminar(estudiante.getId());
            cargarLista();
        });
        recyclerView.setAdapter(adapter);
    }
}