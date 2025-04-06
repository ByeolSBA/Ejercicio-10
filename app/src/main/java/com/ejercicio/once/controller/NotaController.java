package com.ejercicio.once.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ejercicio.once.model.DatabaseHelper;
import com.ejercicio.once.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaController {
    private DatabaseHelper dbHelper;

    public NotaController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertar(int idEstudiante, double valor) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_estudiante", idEstudiante);
        values.put("valor", valor);
        db.insert("notas", null, values);
        db.close();
    }

    public void eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("notas", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Nota> obtenerPorEstudiante(int idEstudiante) {
        List<Nota> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE id_estudiante = ?", new String[]{String.valueOf(idEstudiante)});
        while (cursor.moveToNext()) {
            Nota n = new Nota();
            n.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            n.setIdEstudiante(cursor.getInt(cursor.getColumnIndexOrThrow("id_estudiante")));
            n.setValor(cursor.getDouble(cursor.getColumnIndexOrThrow("valor")));
            lista.add(n);
        }
        cursor.close();
        db.close();
        return lista;
    }

    public double calcularPromedio(int idEstudiante) {
        double suma = 0;
        int cantidad = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT valor FROM notas WHERE id_estudiante = ?", new String[]{String.valueOf(idEstudiante)});
        while (cursor.moveToNext()) {
            suma += cursor.getDouble(cursor.getColumnIndexOrThrow("valor"));
            cantidad++;
        }
        cursor.close();
        db.close();
        return cantidad > 0 ? suma / cantidad : 0.0;
    }
}
