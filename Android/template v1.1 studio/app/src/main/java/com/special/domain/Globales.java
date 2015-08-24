package com.special.domain;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by laynegranadosmogollon on 17/05/15.
 */


public class Globales extends Application {

    String usuario;
    ArrayList<CalificacionActividadSave> seleccionados;


    public Globales() {
        seleccionados = new ArrayList<CalificacionActividadSave>();
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public ArrayList<CalificacionActividadSave> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(ArrayList<CalificacionActividadSave> seleccionados) {
        this.seleccionados = seleccionados;
    }
}
