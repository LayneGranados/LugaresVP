package com.special;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.special.R;
import com.special.domain.Calificacion;
import com.special.domain.CalificacionActividad;
import com.special.domain.CalificacionActividadSave;
import com.special.domain.Globales;
import com.special.menu.ResideMenu;
import com.special.utils.JSONUtil;
import com.special.utils.UICircularImage;
import com.special.utils.UIParallaxScroll;
import com.special.utils.UISwipableList;
import com.special.utils.UITabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class CreateSupervisionActivity extends Activity {

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
    //ArrayList<CalificacionActividadSave> evaluacion;
    JSONArray arrayevaluaciones;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.crear_supervision);
        Intent myIntent = getIntent(); // gets the previously created intent
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
        //TextView mSum = (TextView) findViewById(R.id.sumary);
        //mShare = (UICircularImage) findViewById(R.id.action1);
        UITabs tab = (UITabs) findViewById(R.id.toggle);

        mNavigationTop.getBackground().setAlpha(0);
        mNavigationTitle.setVisibility(View.INVISIBLE);

        mImageView.bringToFront();
        Button btnGuardarEvaluacion = (Button) findViewById(R.id.buttonGuardarEvaluacion);
        btnGuardarEvaluacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //evaluacion = new ArrayList<CalificacionActividadSave>();
                arrayevaluaciones = new JSONArray();
                for(int i = 0;i<listView.getChildCount();i++){
                    System.out.println("Child count: " + listView.getChildCount());
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
                    //evaluacion.add(new CalificacionActividadSave(Integer.parseInt(x), nombreCalificacion));
                }
            new evaluacionJson().execute();
            }

        });



        Bundle bundle = getIntent().getExtras();

        final int top = 0;//bundle.getInt(PACKAGE + ".top");
        final int left = 0;//bundle.getInt(PACKAGE + ".left");
        final int width = 0;//bundle.getInt(PACKAGE + ".width");
        final int height = 0;//bundle.getInt(PACKAGE + ".height");

        String sum = "desc";//bundle.getString("descr");
        imgId = 1;//bundle.getInt("img");

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



        ArrayList<ListItem> data = generateData();
        for (int i = 0; i < calificaciones.size(); i++) {
            getApplicationContext();
            View v = DetailListAdapterCrearSupervision.getView(calificaciones.get(i), this,getApplicationContext());
            listView.addView(v);
        }


        //mTitleView.setText(title);
        //mSum.setText(sum);
        //mImageView.setImageResource(imgId);


        mNavigationBackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

        /*mShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Toast.makeText(CreateSupervisionActivity.this, "Clicked Share", Toast.LENGTH_SHORT).show();
            }
        });*/

        tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.toggle1:
                        mTextView.setVisibility(View.GONE);
                        listView.setVisibility(LinearLayout.VISIBLE);
                        return;
                    case R.id.toggle2:
                        mTextView.setVisibility(View.VISIBLE);
                        listView.setVisibility(LinearLayout.GONE);
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

    private ArrayList<ListItem> generateData(){
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        ArrayList<String> str = new ArrayList<>();
        items.add(new ListItem("a", str));
        items.add(new ListItem("s", str));
        items.add(new ListItem("d", str));
        items.add(new ListItem("f", str));
        return items;
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
            //Difference between the heights, important to not add margin or remove mNavigationTitle.
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
            /*if (res) {
                pasar = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(pasar);
            } else {
                Toast.makeText(CreateSupervisionActivity.this, "Usuario y/ Contraseña Incorrectos. Por favor, intentelo nuevamente.", Toast.LENGTH_SHORT).show();
            }*/
        }

        @Override
        protected Object[] doInBackground(Void... arg0) {
            Object[] res = new Object[5];
            // VERIFICACION DE USUARIO Y PASSWORD CORRECTO
            res[0] = false;
            try {
                final Globales globales = (Globales) getApplicationContext();
                String usuario = globales.getUsuario();
                JSONObject obj = null;

                Object[] observaciones = JSONUtil.guardarEvaluacion("1", "admin", arrayevaluaciones);
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


            } catch (Exception e) {
                Log.i("valores", "Error al leer el json");
                e.printStackTrace();
            }
            return res;
        }
    }





}
