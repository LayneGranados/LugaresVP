package com.special.domain;

import java.io.Serializable;

/**
 * Created by laygrana on 14/08/15.
 */
public class Calificacion implements Serializable {

    private int id;
    private String nombre;

    public Calificacion() {
    }

    public Calificacion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
