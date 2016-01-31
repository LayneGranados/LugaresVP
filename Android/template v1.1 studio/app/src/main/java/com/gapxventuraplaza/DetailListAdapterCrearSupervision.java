package com.gapxventuraplaza;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gapxventuraplaza.domain.Calificacion;
import com.gapxventuraplaza.domain.CalificacionActividad;

import java.util.ArrayList;
import java.util.List;

public class DetailListAdapterCrearSupervision {



    @SuppressLint("InflateParams")
		public static View getView(CalificacionActividad item, Context context, Context app) {
 
            // 1. Create inflater 
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater
            View rowView = inflater.inflate(R.layout.fragment_list_item, null, false);
 
            // 3. Get the two text view from the rowView
            TextView labelViewNombre = (TextView) rowView.findViewById(R.id.item_nombre_actividad);
            TextView labelViewId = (TextView) rowView.findViewById(R.id.item_id_actividad);
            //TextView valueView = (TextView) rowView.findViewById(R.id.item_rol);


 
            // 4. Set the text for textView 
            labelViewNombre.setText(item.getNombreActividad());
            labelViewId.setText(String.valueOf(item.getActividadId()));




        Spinner cals = (Spinner) rowView.findViewById(R.id.calificaciones);
        List<String> list = new ArrayList<String>();
        for(Calificacion c: item.getCalifaciones()){
            list.add(c.getNombre());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cals.setAdapter(dataAdapter);
        cals.setOnItemSelectedListener(new CustomOnItemSelectedListener(item.getActividadId(),app));

            return rowView;
        }
}