package com.gapxventuraplaza.domain;

import java.util.ArrayList;

/**
 * Created by laygrana on 14/08/15.
 */
public class Supervision {

    private ArrayList<Evaluacion> evaluaciones;
    private int id;
    private int lugar;
    private String usuario;
    private int actividad;
    private String nombreCalificacion;
    private String fecha;
    private Boolean encontrado;


    public Supervision() {
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public String getNombreCalificacion() {
        return nombreCalificacion;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreCalificacion = nombreActividad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    public void setNombreCalificacion(String nombreCalificacion) {
        this.nombreCalificacion = nombreCalificacion;
    }
}

