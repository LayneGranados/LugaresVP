package com.special.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by laygrana on 14/08/15.
 */
 public class CalificacionActividad implements Serializable {

    private ArrayList<Calificacion> califaciones;
    private int actividadId;
    private String nombreActividad;

    public CalificacionActividad() {
    }

    public CalificacionActividad(ArrayList<Calificacion> califaciones, int actividadId, String nombreActividad) {
        this.califaciones = califaciones;
        this.actividadId = actividadId;
        this.nombreActividad = nombreActividad;
    }

    public ArrayList<Calificacion> getCalifaciones() {
        return califaciones;
    }

    public void setCalifaciones(ArrayList<Calificacion> califaciones) {
        this.califaciones = califaciones;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
}


