package com.special.domain;

/**
 * Created by laygrana on 18/08/15.
 */
public class CalificacionActividadSave {
    private int idActividad;
    private String nombreCalificacion;

    public CalificacionActividadSave() {
    }

    public CalificacionActividadSave(int idActividad, String nombreCalificacion) {
        this.idActividad = idActividad;
        this.nombreCalificacion = nombreCalificacion;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreCalificacion() {
        return nombreCalificacion;
    }

    public void setNombreCalificacion(String nombreCalificacion) {
        this.nombreCalificacion = nombreCalificacion;
    }
}
