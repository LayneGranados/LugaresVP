package com.gapxventuraplaza;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import com.gapxventuraplaza.domain.Calificacion;
import com.gapxventuraplaza.domain.CalificacionActividad;

public class ListFragment extends Fragment {

    private View parentView;
    private ListView listView;
    private ListAdapter mAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_list, container, false);
        listView   = (ListView) parentView.findViewById(R.id.listView);
        initView();
        return parentView;
    }

    @SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void initView(){
    	//Getting width of display, could be usefull for scaling bitmaps
    	Display display = getActivity().getWindowManager().getDefaultDisplay();
    	int width;
    	if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2){
    		Point size = new Point();
    		display.getSize(size);
    		width = size.x;
    	} else{
        	width = display.getWidth();
    	}
    	
    	mAdapter = new ListAdapter(getActivity(), getListData(), width);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                CalificacionActividad item = (CalificacionActividad) listView.getAdapter().getItem(i);
                
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("tipo_servicio", item.getActividadId());
                intent.putExtra("reserva", item.getNombreActividad());
                intent.putExtra("descr", "layne granados");
                System.out.println("Hizo clic en uno de los de la lista");
                startActivity(intent);
            }
        });
    }

    private ArrayList<CalificacionActividad> getListData(){
        //ArrayList<CalificacionActividad> servicios=  (ArrayList<CalificacionActividad>)this.getActivity().getIntent().getExtras().getSerializable("servicios");
        //System.out.println("TAMAÑO DE LOS SERVICIOS EN FRAGMENT: "+servicios.size());

        ArrayList<CalificacionActividad> listData = new ArrayList<CalificacionActividad>();

        CalificacionActividad ca1 = new CalificacionActividad();
        ArrayList<Calificacion> cs1 = new ArrayList<Calificacion>();
        Calificacion c1 = new Calificacion();
        c1.setId(1);
        c1.setNombre("Completo");
        Calificacion c2 = new Calificacion();
        c2.setId(1);
        c2.setNombre("Incompleto");
        cs1.add(c1);
        cs1.add(c2);

        ca1.setCalifaciones(cs1);
        ca1.setActividadId(1);
        ca1.setNombreActividad("acti");




        listData.add(ca1);
        return listData;

    }
}
