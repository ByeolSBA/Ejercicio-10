package com.ejercicio.once.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ejercicio.once.R;
import com.ejercicio.once.model.Nota;

import java.util.List;
import java.util.function.Consumer;

public class NotaListAdapter extends BaseAdapter {

    private Context context;
    private List<Nota> notas;
    private Consumer<Nota> onDeleteClick;

    public NotaListAdapter(Context context, List<Nota> notas, Consumer<Nota> onDeleteClick) {
        this.context = context;
        this.notas = notas;
        this.onDeleteClick = onDeleteClick;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Nota nota = notas.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        }

        TextView tvValor = convertView.findViewById(R.id.tvNota);
        ImageButton btnEliminar = convertView.findViewById(R.id.btnEliminarNota);

        tvValor.setText(String.valueOf(nota.getValor()));
        btnEliminar.setOnClickListener(v -> onDeleteClick.accept(nota));

        return convertView;
    }
}
