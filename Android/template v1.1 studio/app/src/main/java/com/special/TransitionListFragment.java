package com.special;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;

import com.special.R;
import com.special.domain.Calificacion;
import com.special.domain.CalificacionActividad;
import com.special.domain.Globales;
import com.special.menu.ResideMenu;
import com.special.utils.JSONUtil;
import com.special.utils.UISwipableList;

import org.json.JSONArray;
import org.json.JSONObject;

public class TransitionListFragment extends Fragment {

    private View parentView;
    private UISwipableList listView;
    private TransitionListAdapter mAdapter;
    private ResideMenu resideMenu;
    private String lugar="";
    private ArrayList<ListItemSupervision> listData;
    private TextView title;
    private TextView desc;

    private String PACKAGE = "IDENTIFY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_list_transition, container, false);
        listView   = (UISwipableList) parentView.findViewById(R.id.listView);

        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        title = (TextView)parentView.findViewById(R.id.title);
        desc  = (TextView)parentView.findViewById(R.id.desc);

        Bundle bundle = this.getArguments();
        lugar = bundle.getString("idlugar");
        this.getListData();


        return parentView;
    }

    private void initView(String t, String d){

        Button btnCrearEvaluacion = (Button)parentView.findViewById(R.id.buttonCreateEvaluacion);
        btnCrearEvaluacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent pasar = new Intent(getActivity(), CreateSupervisionActivity.class);
                new calificacionJSON().execute();
            }
        });

    	mAdapter = new TransitionListAdapter(getActivity(), listData);
        listView.setActionLayout(R.id.hidden_view);
        listView.setItemLayout(R.id.front_layout);
        listView.setAdapter(mAdapter);
        listView.setIgnoredViewHandler(resideMenu);
        title.setText(t);
        desc.setText(d);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewa, int i, long l) { 
                ListItem item = (ListItem) listView.getAdapter().getItem(i);
        
                Intent intent = new Intent(getActivity(), TransitionDetailActivity.class);
                
                Bundle bundle = new Bundle();
                bundle.putString("title", "Fecha: "+item.getNombreActividad());
                bundle.putString("descr", "Supervisado por: "+item.getNombreActividad());
                int[] screen_location = new int[2];
                bundle.putInt(PACKAGE + ".left", screen_location[0]);
                bundle.putInt(PACKAGE + ".top", screen_location[1]);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    private void getListData(){
        listData = new ArrayList<ListItemSupervision>();
        try {
            new supervisiones().execute();

        } catch (Exception e) {
            Log.i("valores", "Error al leer el json");
            e.printStackTrace();
        }
    }

    class supervisiones extends AsyncTask<Void, Void, Object[]> implements Serializable {
        private Intent pasar;

        protected void onPostExecute(Object[] result) {

            String title = (String)result[0];
            String desc = (String)result[1];
            initView(title,desc);
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];
            try {

                Object[] respuesta = JSONUtil.supervisionesDeLugar(lugar);
                String x = String.valueOf(respuesta[0]); // datos json
                x = x.replace("\n", "");
                JSONArray array = new JSONArray(x);
                boolean ya=false;
                for(int i=0;i<array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                    listData.add(new ListItemSupervision("Fecha: "+obj.getString("fecha"), "Supervisado por: "+obj.getString("supervisor"), null, null));
                    if(!ya){
                       res[0]=obj.getString("lugartitle");
                       res[1]=obj.getString("lugardesc");
                       ya=true;
                    }
                }

            } catch (Exception e) {
                Log.i("valores", "Error al leer el json");
                e.printStackTrace();
            }
            return res;
        }

    }


    class calificacionJSON extends AsyncTask<Void, Void, Object[]> implements Serializable {
        private Intent pasar;
        protected void onPostExecute(Object[] result) {

            Boolean res = (Boolean) result[3];
            ArrayList<CalificacionActividad> calificaciones = (ArrayList<CalificacionActividad>)result[1];
            Intent pasar = new Intent(getActivity(), CreateSupervisionActivity.class);
            pasar.putExtra("calificaciones", calificaciones);
            startActivity(pasar);
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];
            res[0] = false;
            try {
                JSONObject obj = null;

                Object[] resultados = JSONUtil.calificacionActividadPorlugar("1");
                String x = String.valueOf(resultados[0]);
                x = x.replace("\n", "");
                res[1] = String.valueOf(resultados[1]);

                ArrayList<CalificacionActividad> calificaciones  = new ArrayList<>();

                JSONArray array = new JSONArray(x);
                for(int i =0;i<array.length();i++){
                    obj = array.getJSONObject(i);
                    CalificacionActividad actividad = new CalificacionActividad();

                    int idactividad = obj.getInt("idactividad");
                    boolean band = false;
                    for(int j=0;j<calificaciones.size()&&!band;j++){
                        CalificacionActividad c = calificaciones.get(j);

                        if(c.getActividadId()==idactividad){
                            band=true;
                            ArrayList<Calificacion> cals = c.getCalifaciones();
                            cals.add(new Calificacion(obj.getInt("idcalificacion"), obj.getString("nombrecalificacion")));
                            c.setCalifaciones(cals);
                            calificaciones.set(j,c);
                        }
                    }

                    if(!band){
                        ArrayList<Calificacion> cals = new ArrayList<>();
                        cals.add(new Calificacion(obj.getInt("idcalificacion"), obj.getString("nombrecalificacion")));
                        actividad.setCalifaciones(cals);
                        actividad.setActividadId(obj.getInt("idactividad"));
                        actividad.setNombreActividad(obj.getString("nombreactividad"));
                        calificaciones.add(actividad);
                    }
                }

                res[1] = calificaciones;
            } catch (Exception e) {
                Log.i("valores", "Error al leer el json");
                e.printStackTrace();
            }
            return res;
        }
    }

}
