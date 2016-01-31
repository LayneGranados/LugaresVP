package com.gapxventuraplaza.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by laygrana on 14/08/15.
 */
public class Supervision {

    private ArrayList<Evaluacion> evaluaciones;
    private int supervisor;
    private Date fecha;
    private int lugar;

    public Supervision() {
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
}

