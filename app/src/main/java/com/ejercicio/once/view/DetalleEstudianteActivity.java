package com.ejercicio.once.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ejercicio.once.R;
import com.ejercicio.once.adapter.NotaListAdapter;
import com.ejercicio.once.controller.EstudianteController;
import com.ejercicio.once.controller.NotaController;
import com.ejercicio.once.model.Nota;

import java.util.List;

public class DetalleEstudianteActivity extends AppCompatActivity {

    private TextView tvNombre, tvPromedio;
    private EditText etNota;
    private Button btnAgregarNota;
    private ListView listViewNotas;

    private EstudianteController estudianteController;
    private NotaController notaController;
    private int idEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_estudiante);

        idEstudiante = getIntent().getIntExtra("idEstudiante", -1);

        estudianteController = new EstudianteController(this);
        notaController = new NotaController(this);

        tvNombre = findViewById(R.id.tvNombreEstudiante);
        tvPromedio = findViewById(R.id.tvPromedio);
        etNota = findViewById(R.id.etNota);
        btnAgregarNota = findViewById(R.id.btnAgregarNota);
        listViewNotas = findViewById(R.id.lvNotas);

        // Validar ID válido antes de continuar
        if (idEstudiante != -1) {
            tvNombre.setText(estudianteController.obtenerPorId(idEstudiante).getNombre());
            cargarNotas();

            btnAgregarNota.setOnClickListener(v -> {
                String texto = etNota.getText().toString().trim();
                try {
                    double valor = Double.parseDouble(texto);
                    notaController.insertar(idEstudiante, valor);
                    etNota.setText("");
                    cargarNotas();
                } catch (NumberFormatException e) {
                    etNota.setError("Ingrese un número válido");
                }
            });
        } else {
            tvNombre.setText("Estudiante no encontrado");
        }
    }

    private void cargarNotas() {
        List<Nota> notas = notaController.obtenerPorEstudiante(idEstudiante);
        listViewNotas.setAdapter(new NotaListAdapter(this, notas, nota -> {
            notaController.eliminar(nota.getId());
            cargarNotas();
        }));

        double promedio = notaController.calcularPromedio(idEstudiante);
        tvPromedio.setText("Promedio: " + promedio);
    }
}
