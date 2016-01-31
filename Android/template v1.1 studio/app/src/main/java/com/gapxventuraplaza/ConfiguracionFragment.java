package com.gapxventuraplaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;


public class ConfiguracionFragment extends Fragment {

    private View parentView;
    MainActivity parentActivity;
    Format formatter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_list_configuracion, container, false);
        parentActivity = (MainActivity) getActivity();

        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        TextView title = (TextView)parentView.findViewById(R.id.titleConfiguracion);
        title.setText("Configuración");
        Button actualizar = (Button)parentView.findViewById(R.id.btnActualizarEventos);
        Button cerrarSesion = (Button)parentView.findViewById(R.id.btnCerrarSesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent pasar = new Intent(parentActivity.getApplicationContext(), LoginActivity.class);
                startActivity(pasar);
            }
        });

        return parentView;
    }


}
