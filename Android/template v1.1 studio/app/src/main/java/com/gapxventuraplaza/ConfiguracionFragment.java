package com.gapxventuraplaza;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gapxventuraplaza.database.SupervisionDataSource;
import com.gapxventuraplaza.domain.Globales;
import com.gapxventuraplaza.domain.Supervision;
import com.gapxventuraplaza.menu.ResideMenu;
import com.gapxventuraplaza.utils.JSONUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;


public class ConfiguracionFragment extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;
    Globales app;

    MainActivity parentActivity;
    Format formatter;

    private SupervisionDataSource datasource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_list_configuracion, container, false);
        parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
        app = ((Globales)parentActivity.getApplicationContext());
        datasource = new SupervisionDataSource(parentActivity);
        datasource.open();

        TextView title = (TextView)parentView.findViewById(R.id.titleConfiguracion);
        title.setText("Configuración");
        Button actualizar = (Button)parentView.findViewById(R.id.btnActualizarEventos);
        Button cerrarSesion = (Button)parentView.findViewById(R.id.btnCerrarSesion);
        TextView cantidadCalificacionesActualizar = (TextView)parentView.findViewById(R.id.textoActualizarSupervisiones);
        List<Supervision> supervisiones = datasource.getAllSupervisiones();
        int cantidadCalificaciones = supervisiones.size();
        cantidadCalificacionesActualizar.setText("Actualizar Supervisiones: "+cantidadCalificaciones);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent pasar = new Intent(parentActivity.getApplicationContext(), LoginActivity.class);
                startActivity(pasar);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new evaluacionJson().execute();
            }
        });
        return parentView;
    }


    class evaluacionJson extends AsyncTask<Void, Void, Object[]> implements Serializable {
        private Intent pasar;

        protected void onPostExecute(Object[] result) {
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];

            res[0] = false;
            List<Supervision> supervisiones = datasource.getAllSupervisiones();
            List<Integer> idSupervisiones = new ArrayList<Integer>();
            try {
                if(!supervisiones.isEmpty()){

                    for(int i=0;i<supervisiones.size();i++){
                        Supervision supervision = supervisiones.get(i);

                        if(supervision.getEncontrado()){
                            JSONArray arrayCalificaciones = new JSONArray();
                            JSONObject obj = new JSONObject();

                            for(int j=i;j<supervisiones.size();j++){
                                Supervision segunda = supervisiones.get(j);
                                if(supervision.getFecha().equalsIgnoreCase(segunda.getFecha())){

                                    try{
                                        obj.put("idactividad", supervisiones.get(j).getActividad());
                                        obj.put("nombrecalificacion", supervisiones.get(j).getNombreCalificacion());
                                        arrayCalificaciones.put(obj);
                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                    segunda.setEncontrado(false);
                                    supervisiones.set(j, segunda);
                                    idSupervisiones.add(supervisiones.get(j).getId());
                                    arrayCalificaciones.put(obj);
                                }
                            }

                            try{
                                Object[] observaciones = JSONUtil.guardarEvaluacion(String.valueOf(supervision.getLugar()), supervision.getUsuario(), arrayCalificaciones, supervision.getFecha());
                                String x = String.valueOf(observaciones[0]);
                                x = x.replace("\n", "");
                                res[1] = String.valueOf(observaciones[1]);
                                System.out.println("Resultado de guardar supervision" + x);
                                if(!x.equalsIgnoreCase("null")) {
                                    if(x.equalsIgnoreCase("false")){
                                        res[3] = false;
                                    }else if(x.equalsIgnoreCase("true")){
                                        res[3] = true;
                                    }
                                }
                                else {
                                    res[3] = false;
                                }
                                if((Boolean)res[3]){
                                    for(Integer id: idSupervisiones){
                                        datasource.deleteSupervision(id);
                                    }
                                }

                            } catch (Exception e) {
                                Log.i("valores", "Error al leer el json");
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return res;
        }
    }




}
