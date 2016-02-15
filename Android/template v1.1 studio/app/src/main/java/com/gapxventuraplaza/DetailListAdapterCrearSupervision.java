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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.fragment_list_item, null, false);

            TextView labelViewNombre = (TextView) rowView.findViewById(R.id.item_nombre_actividad);
            TextView labelViewId = (TextView) rowView.findViewById(R.id.item_id_actividad);
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