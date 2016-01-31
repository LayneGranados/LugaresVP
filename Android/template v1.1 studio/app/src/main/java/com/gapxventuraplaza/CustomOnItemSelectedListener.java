package com.gapxventuraplaza;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.gapxventuraplaza.domain.CalificacionActividadSave;
import com.gapxventuraplaza.domain.Globales;

import java.util.ArrayList;

/**
 * Created by laygrana on 18/08/15.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    int idActividad;
    Context context;

    public CustomOnItemSelectedListener(int idActividad,Context context) {
        this.idActividad=idActividad;
        this.context = context;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        final Globales gbs = (Globales)this.context;
        CalificacionActividadSave cas = new CalificacionActividadSave(this.idActividad,parent.getItemAtPosition(pos).toString());
        ArrayList<CalificacionActividadSave> seleccionados = gbs.getSeleccionados();

        boolean encontrado = false;
        if(seleccionados==null);{
            seleccionados = new ArrayList<CalificacionActividadSave>();
            System.out.println("es null");
        }

        System.out.println("size de seleccionados: "+seleccionados.size());
        for(int i=0;i<seleccionados.size()&&!encontrado;i++) {
            if(seleccionados.get(i).getIdActividad()==this.idActividad){
                seleccionados.set(i,cas);
                encontrado=true;
            }
        }
        if(!encontrado){
            seleccionados.add(cas);
            System.out.println("no lo encontro");
        }
    gbs.setSeleccionados(seleccionados);
        System.out.println("tamaño array : " + seleccionados.size());
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

