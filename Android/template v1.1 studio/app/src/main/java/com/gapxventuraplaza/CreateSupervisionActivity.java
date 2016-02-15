package com.gapxventuraplaza;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gapxventuraplaza.database.SupervisionDataSource;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.gapxventuraplaza.domain.CalificacionActividad;
import com.gapxventuraplaza.domain.Globales;
import com.gapxventuraplaza.utils.JSONUtil;
import com.gapxventuraplaza.utils.UICircularImage;
import com.gapxventuraplaza.utils.UIParallaxScroll;
import com.gapxventuraplaza.utils.UITabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class CreateSupervisionActivity extends Activity {

    private static Context mContext;
    private SupervisionDataSource datasource;
    //Configuration
    public static final int DURATION = 500; // in ms
    public static final String PACKAGE = "IDENTIFY";

    //UI Elements
    private UICircularImage mImageView;
    private TextView mTextView;
    private RelativeLayout mLayoutContainer;
    private FrameLayout mNavigationTop;
    private TextView mNavigationTitle;
    private Button mNavigationBackBtn;
    private TextView mTitleView;
    private UICircularImage mShare;
    ArrayList<CalificacionActividad> calificaciones;
    //Vars
    private int delta_top;
    private int delta_left;
    private float scale_width;
    private float scale_height;
    int imgId;

    JSONArray arrayevaluaciones;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        datasource = new SupervisionDataSource(this);
        datasource.open();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.crear_supervision);
        Intent myIntent = getIntent();
        calificaciones = (ArrayList<CalificacionActividad>)getIntent().getSerializableExtra("calificaciones");

        String secondKeyName= myIntent.getStringExtra("secondKeyName");
        ((UIParallaxScroll) findViewById(R.id.scroller)).setOnScrollChangedListener(mOnScrollChangedListener);

        mImageView = (UICircularImage) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.contact);
        mNavigationTop = (FrameLayout) findViewById(R.id.layout_top);
        mNavigationTitle = (TextView) findViewById(R.id.titleBar);
        mLayoutContainer = (RelativeLayout) findViewById(R.id.bg_layout);
        mTitleView = (TextView) findViewById(R.id.title);
        mNavigationBackBtn = (Button) findViewById(R.id.title_bar_left_menu);
        final LinearLayout listView = (LinearLayout) findViewById(R.id.listView);
        UITabs tab = (UITabs) findViewById(R.id.toggle);

        mNavigationTop.getBackground().setAlpha(0);
        mNavigationTitle.setVisibility(View.INVISIBLE);

        mImageView.bringToFront();
        Button btnGuardarEvaluacion = (Button) findViewById(R.id.buttonGuardarEvaluacion);
        btnGuardarEvaluacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                arrayevaluaciones = new JSONArray();
                for(int i = 0;i<listView.getChildCount();i++){
                    View v = listView.getChildAt(i);
                    TextView labelViewNombre = (TextView) v.findViewById(R.id.item_id_actividad);
                    Spinner spinner = (Spinner)v.findViewById(R.id.calificaciones);
                    String nombreCalificacion =  String.valueOf(spinner.getSelectedItem());
                    String x = (String) labelViewNombre.getText();
                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("idactividad", x);
                        obj.put("nombrecalificacion", nombreCalificacion);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arrayevaluaciones.put(obj);
                }
            new evaluacionJson().execute();
            }

        });



        Bundle bundle = getIntent().getExtras();

        final int top = 0;
        final int left = 0;
        final int width = 0;
        final int height = 0;

        String sum = "desc";
        imgId = 1;

        //Our Animation initialization

        ViewTreeObserver observer = mImageView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                mImageView.getViewTreeObserver().removeOnPreDrawListener(this);

                int[] screen_location = new int[2];
                mImageView.getLocationOnScreen(screen_location);

                delta_left = left - screen_location[0];
                delta_top = top - screen_location[1];

                scale_width = (float) width / mImageView.getWidth();
                scale_height = (float) height / mImageView.getHeight();

                runEnterAnimation();

                return true;
            }
        });

        for (int i = 0; i < calificaciones.size(); i++) {
            getApplicationContext();
            View v = DetailListAdapterCrearSupervision.getView(calificaciones.get(i), this,getApplicationContext());
            listView.addView(v);
        }

        mNavigationBackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

        tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.toggle1:
                        mTextView.setVisibility(View.GONE);
                        listView.setVisibility(LinearLayout.VISIBLE);
                        return;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        runExitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(0, 0);
    }

    private void runEnterAnimation() {

        ViewHelper.setPivotX(mImageView, 0.f);
        ViewHelper.setPivotY(mImageView, 0.f);
        ViewHelper.setScaleX(mImageView, scale_width);
        ViewHelper.setScaleY(mImageView, scale_height);
        ViewHelper.setTranslationX(mImageView, delta_left);
        ViewHelper.setTranslationY(mImageView, delta_top);

        animate(mImageView).
                setDuration(DURATION).
                scaleX(1.f).
                scaleY(1.f).
                translationX(0.f).
                translationY(0.f).
                setInterpolator(new DecelerateInterpolator()).
                setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }
                });

        ObjectAnimator bg_anim = ObjectAnimator.ofFloat(mLayoutContainer, "alpha", 0f, 1f);
        bg_anim.setDuration(DURATION);
        bg_anim.start();

    }

    private void runExitAnimation(final Runnable end_action) {

        ViewHelper.setPivotX(mImageView, 0.f);
        ViewHelper.setPivotY(mImageView, 0.f);
        ViewHelper.setScaleX(mImageView, 1.f);
        ViewHelper.setScaleY(mImageView, 1.f);
        ViewHelper.setTranslationX(mImageView, 0.f);
        ViewHelper.setTranslationY(mImageView, 0.f);

        animate(mImageView).
                setDuration(DURATION).
                scaleX(scale_width).
                scaleY(scale_height).
                translationX(delta_left).
                translationY(delta_top).
                setInterpolator(new DecelerateInterpolator()).
                setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        end_action.run();
                    }
                });

        ObjectAnimator bg_anim = ObjectAnimator.ofFloat(mLayoutContainer, "alpha", 1f, 0f);
        bg_anim.setDuration(DURATION);
        bg_anim.start();
    }

    private UIParallaxScroll.OnScrollChangedListener mOnScrollChangedListener = new UIParallaxScroll.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            final float headerHeight = ViewHelper.getY(mTitleView) - (mNavigationTop.getHeight() - mTitleView.getHeight());
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mNavigationTop.getBackground().setAlpha(newAlpha);

            Animation animationFadeIn = AnimationUtils.loadAnimation(CreateSupervisionActivity.this, R.anim.fadein);
            Animation animationFadeOut = AnimationUtils.loadAnimation(CreateSupervisionActivity.this,R.anim.fadeout);

            if (newAlpha == 255 && mNavigationTitle.getVisibility() != View.VISIBLE && !animationFadeIn.hasStarted()){
                mNavigationTitle.setVisibility(View.VISIBLE);
                mNavigationTitle.startAnimation(animationFadeIn);
            } else if (newAlpha < 255 && !animationFadeOut.hasStarted() && mNavigationTitle.getVisibility() != View.INVISIBLE)  {
                mNavigationTitle.startAnimation(animationFadeOut);
                mNavigationTitle.setVisibility(View.INVISIBLE);

            }
        }
    };


    class evaluacionJson extends AsyncTask<Void, Void, Object[]> implements Serializable {
        private Intent pasar;

        protected void onPostExecute(Object[] result) {

            Boolean res = (Boolean) result[3];
            String msg = (String)result[1];
            if (res) {
                pasar = new Intent(getApplicationContext(), MainActivity.class);
                final Globales globales = (Globales) getApplicationContext();
                startActivity(pasar);
            } else {
                Toast.makeText(CreateSupervisionActivity.this, "Se produjo un error al guardar la supervisión, por favor, confirme con el administrador del sistema.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];


            res[0] = false;
            try {
                final Globales globales = (Globales) getApplicationContext();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();

                Object[] observaciones = JSONUtil.guardarEvaluacion(String.valueOf(globales.getLugar()), globales.getUsuario(), arrayevaluaciones, dateFormat.format(date));
                String x = String.valueOf(observaciones[0]);
                x = x.replace("\n", "");
                res[1] = String.valueOf(observaciones[1]);
                System.out.println("Resultado de guardar supervision"+x);
                if(!x.equalsIgnoreCase("null")) {

                        if(x.equalsIgnoreCase("false")){
                            guardarSupervisiones();
                            res[3] = false;
                        }else if(x.equalsIgnoreCase("true")){
                            res[3] = true;
                        }
                }
                else {
                    guardarSupervisiones();
                    res[3] = false;
                }
            } catch (Exception e) {
                Log.i("valores", "Error al leer el json");
                e.printStackTrace();
            }
            return res;
        }
    }

    private void guardarSupervisiones(){
        final Globales globales = (Globales) getApplicationContext();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String date = formatter.format(new Date());
        for(int i=0;i<arrayevaluaciones.length();i++){
            try {
                JSONObject obj = arrayevaluaciones.getJSONObject(i);
                datasource.createSupervision(globales.getLugar() , globales.getUsuario(), obj.getInt("idactividad"), obj.getString("nombrecalificacion"), date);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }





}
