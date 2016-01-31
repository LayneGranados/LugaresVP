package com.gapxventuraplaza;

import java.io.Serializable;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gapxventuraplaza.domain.*;
import com.gapxventuraplaza.utils.JSONUtil;

public class LoginActivity extends Activity {

    LinearLayout layoutOfPopup;
    PopupWindow popupMessage;
    Button popupButton, insidePopupButton;
    TextView popupText;
    private static Context mContext;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        Button btnInicioSesion = (Button) findViewById(R.id.btnInicioSesion);
        btnInicioSesion.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new usersJson().execute();
            }
        });
    }

    class usersJson extends AsyncTask<Void, Void, Object[]> implements Serializable {
        private Intent pasar;

        protected void onPostExecute(Object[] result) {

            Boolean res = (Boolean) result[3];
            String msg = (String)result[1];

            final Globales globales = (Globales) getApplicationContext();
            globales.setUsuario((String)result[2]);

            if (res) {
                pasar = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(pasar);
            } else {
                Toast.makeText(LoginActivity.this, "Usuario y/ Contraseña Incorrectos. Por favor, intentelo nuevamente.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];
            // VERIFICACION DE USUARIO Y PASSWORD CORRECTO
            res[0] = false;
            try {
                EditText userEdit = (EditText) findViewById(R.id.editUsuario);
                EditText passEdit = (EditText) findViewById(R.id.editPassword);
                JSONObject obj = null;

                Object[] observaciones = JSONUtil.login(userEdit.getText().toString(), passEdit.getText().toString());
                String x = String.valueOf(observaciones[0]);
                x = x.replace("\n", "");
                System.out.println("Json devuelto de login: "+x);
                res[1] = String.valueOf(observaciones[1]);

                if(x.equalsIgnoreCase("true")) {
                    res[3] = true;
                }
                else {
                    res[3] = false;
                }

                res[2] = userEdit.getText().toString();
            } catch (Exception e) {
                Log.i("valores", "Error al leer el json");
                e.printStackTrace();
            }
            return res;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    class IniciarSesionButtonOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), ScanCode.class);
            startActivity(i);
        }

    }


}
