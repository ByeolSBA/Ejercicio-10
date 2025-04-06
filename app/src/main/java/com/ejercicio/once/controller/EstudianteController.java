package com.ejercicio.once.controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ejercicio.once.model.DatabaseHelper;
import com.ejercicio.once.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteController {
    private DatabaseHelper dbHelper;

    public EstudianteController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertar(String nombre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        db.insert("estudiantes", null, values);
        db.close();
    }

    public void actualizar(int id, String nuevoNombre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nuevoNombre);
        db.update("estudiantes", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("notas", "id_estudiante = ?", new String[]{String.valueOf(id)});
        db.delete("estudiantes", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Estudiante> obtenerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM estudiantes", null);
        while (cursor.moveToNext()) {
            Estudiante e = new Estudiante();
            e.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            e.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            lista.add(e);
        }
        cursor.close();
        db.close();
        return lista;
    }

    public Estudiante obtenerPorId(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM estudiantes WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            Estudiante e = new Estudiante();
            e.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            e.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            cursor.close();
            db.close();
            return e;
        }
        cursor.close();
        db.close();
        return null;
    }
}