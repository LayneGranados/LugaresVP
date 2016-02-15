package com.gapxventuraplaza;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gapxventuraplaza.database.SupervisionDataSource;
import com.gapxventuraplaza.domain.Supervision;
import com.gapxventuraplaza.utils.JSONUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;


public class ConfiguracionFragment extends Activity {


    private SupervisionDataSource datasource;

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_configuracion);

        datasource = new SupervisionDataSource(this);
        datasource.open();


        TextView title = (TextView)findViewById(R.id.titleConfiguracion);
        title.setText("Configuración");
        Button actualizar = (Button)findViewById(R.id.btnActualizarEventos);
        Button cerrarSesion = (Button)findViewById(R.id.btnCerrarSesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent pasar = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(pasar);
            }
        });

    }


    class evaluacionJson extends AsyncTask<Void, Void, Object[]> implements Serializable {
        private Intent pasar;

        protected void onPostExecute(Object[] result) {
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];

            res[0] = false;
            try {


                List<Supervision> supervisiones = datasource.getAllSupervisiones();
                if(!supervisiones.isEmpty()){

                    for(int i=0;i<supervisiones.size();i++){
                        JSONArray arrayCalificaciones = new JSONArray();
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("idactividad", supervisiones.get(i).getActividad());
                            obj.put("nombrecalificacion", supervisiones.get(i).getNombreCalificacion());
                            arrayCalificaciones.put(obj);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        for(int j=i+1;j<supervisiones.size();j++){

                            if(supervisiones.get(i).getFecha().equalsIgnoreCase(supervisiones.get(j).getFecha())){
                                obj = new JSONObject();
                                try{
                                    obj.put("idactividad", supervisiones.get(j).getActividad());
                                    obj.put("nombrecalificacion", supervisiones.get(j).getNombreCalificacion());
                                    arrayCalificaciones.put(obj);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                supervisiones.remove(j);
                            }
                        }
                        try{
                        Object[] observaciones = JSONUtil.guardarEvaluacion(String.valueOf(supervisiones.get(i).getLugar()), supervisiones.get(i).getUsuario(), arrayCalificaciones, supervisiones.get(i).getFecha());
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
                                datasource.deleteSupervision(0);
                            }

                        } catch (Exception e) {
                            Log.i("valores", "Error al leer el json");
                            e.printStackTrace();
                        }

                    }
                }



            return res;
        }
    }




}
