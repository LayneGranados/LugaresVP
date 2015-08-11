package com.special.domain;

import java.io.Serializable;

/**
 * Created by laynegranadosmogollon on 6/05/15.
 */
public class Empresa implements Serializable{

    private static final long serialVersionUID = 7526472295622776147L;

    private int id;
    private String nombre;

    public Empresa(int id, String nombre) {
        this.id = id;
        this.nombre=nombre;
    }

    public Empresa() {
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

    public boolean equals(Empresa empresa) {
        if (this.id != empresa.id)
            return false;
        if (!this.nombre.equalsIgnoreCase(empresa.getNombre()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
