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
import com.special.domain.CalificacionActividad;
import com.special.domain.Globales;
import com.special.menu.ResideMenu;
import com.special.utils.JSONUtil;
import com.special.utils.UICircularImage;
import com.special.utils.UIParallaxScroll;
import com.special.utils.UISwipableList;
import com.special.utils.UITabs;

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
    String title;
    int imgId;

    String lor1 = "lor1";
    String lor3 = "lor2";
    String lor2 = "lor3";
    String lor4 = "lor4";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.crear_supervision);
        Intent myIntent = getIntent(); // gets the previously created intent
        calificaciones = (ArrayList<CalificacionActividad>)getIntent().getSerializableExtra("calificaciones"); // will return "FirstKeyValue"

        String secondKeyName= myIntent.getStringExtra("secondKeyName");
        ((UIParallaxScroll) findViewById(R.id.scroller)).setOnScrollChangedListener(mOnScrollChangedListener);

        mImageView = (UICircularImage) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.contact);
        mNavigationTop = (FrameLayout) findViewById(R.id.layout_top);
        mNavigationTitle = (TextView) findViewById(R.id.titleBar);
        mLayoutContainer = (RelativeLayout) findViewById(R.id.bg_layout);
        mTitleView = (TextView) findViewById(R.id.title);
        mNavigationBackBtn = (Button) findViewById(R.id.title_bar_left_menu);
        TextView mSum = (TextView) findViewById(R.id.sumary);
        mShare = (UICircularImage) findViewById(R.id.action1);
        UITabs tab = (UITabs) findViewById(R.id.toggle);

        mNavigationTop.getBackground().setAlpha(0);
        mNavigationTitle.setVisibility(View.INVISIBLE);

        mImageView.bringToFront();

        Bundle bundle = getIntent().getExtras();

        final int top = 0;//bundle.getInt(PACKAGE + ".top");
        final int left = 0;//bundle.getInt(PACKAGE + ".left");
        final int width = 0;//bundle.getInt(PACKAGE + ".width");
        final int height = 0;//bundle.getInt(PACKAGE + ".height");
        title = "title";//bundle.getString("title");
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


        final LinearLayout listView = (LinearLayout) findViewById(R.id.listView);
        ArrayList<ListItem> data = generateData();
        for (int i = 0; i < calificaciones.size(); i++) {
            View v = DetailListAdapterCrearSupervision.getView(calificaciones.get(i), this);
            listView.addView(v);
        }


        mTitleView.setText(title);
        mSum.setText(sum);
        //mImageView.setImageResource(imgId);
        mNavigationTitle.setText(title);

        mNavigationBackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

        mShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Toast.makeText(CreateSupervisionActivity.this, "Clicked Share", Toast.LENGTH_SHORT).show();
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





}
