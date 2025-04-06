package com.ejercicio.once.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejercicio.once.R;
import com.ejercicio.once.model.Estudiante;

import java.util.List;
import java.util.function.Consumer;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {

    private List<Estudiante> estudiantes;
    private Consumer<Estudiante> onItemClick;
    private Consumer<Estudiante> onDeleteClick;

    public EstudianteAdapter(List<Estudiante> estudiantes, Consumer<Estudiante> onItemClick, Consumer<Estudiante> onDeleteClick) {
        this.estudiantes = estudiantes;
        this.onItemClick = onItemClick;
        this.onDeleteClick = onDeleteClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Estudiante e = estudiantes.get(position);
        holder.tvNombre.setText(e.getNombre());
        holder.itemView.setOnClickListener(v -> onItemClick.accept(e));
        holder.btnEliminar.setOnClickListener(v -> onDeleteClick.accept(e));
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ImageButton btnEliminar;

        ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreEstudiante);
            btnEliminar = itemView.findViewById(R.id.btnEliminarEstudiante);
        }
    }
}