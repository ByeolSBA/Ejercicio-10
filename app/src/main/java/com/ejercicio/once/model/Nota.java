package com.ejercicio.once.model;

public class Nota {
    private int id;
    private int idEstudiante;
    private double valor;

    public Nota() {}

    public Nota(int id, int idEstudiante, double valor) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public double getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}