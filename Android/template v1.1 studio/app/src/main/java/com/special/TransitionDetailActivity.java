package com.special;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.special.R;
import com.special.utils.UICircularImage;
import com.special.utils.UIParallaxScroll;
import com.special.utils.UITabs;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class TransitionDetailActivity extends Activity {

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

    //Vars
    private int delta_top;
    private int delta_left;
    private float scale_width;
    private float scale_height;
	String title;
	int imgId;
	
	String lor1 = "Pellentesque in luctus dui, non egestas nisl. Donec sapien ante, faucibus a sem at, tincidunt dictum quam. Sed vel blandit neque. Maecenas tincidunt at sem vel sodales. Nullam dignissim eros id tellus commodo, eu vulputate massa accumsan.<br><br>Ut eget volutpat turpis. Praesent ac auctor nisi, sed imperdiet augue. Aenean consequat est vel odio molestie pellentesque. Suspendisse rhoncus velit dolor, at ultrices nulla ullamcorper a. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus nec felis elit. Mauris at erat euismod leo sagittis gravida in id magna.";
	String lor2 = "Donec ornare eleifend turpis. Cras consectetur at neque sit amet bibendum. Nulla metus dui, porta vel mollis vitae, ornare sit amet lectus. Integer imperdiet quam eleifend nisl dictum vehicula. Suspendisse pharetra aliquet porttitor. Maecenas nec pharetra purus. Sed scelerisque suscipit faucibus. Etiam hendrerit tellus risus, et interdum tortor facilisis quis.";
	String lor3 = "Etiam tristique, sapien non rhoncus vestibulum, erat augue suscipit velit, vestibulum viverra justo nibh ut nibh. Vivamus pulvinar pharetra scelerisque. Curabitur ullamcorper tristique lacus.";
	String lor4 = "Maecenas id tortor sed purus ultricies tempor. In vulputate feugiat iaculis. Phasellus sem turpis, adipiscing sit amet lacus in, aliquet aliquet eros. Integer euismod, orci et tincidunt iaculis, odio odio vulputate lectus, sed rutrum sapien odio eget sem.";
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        	getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_detail_transition);
        
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

        final int top = bundle.getInt(PACKAGE + ".top");
        final int left = bundle.getInt(PACKAGE + ".left");
        final int width = bundle.getInt(PACKAGE + ".width");
        final int height = bundle.getInt(PACKAGE + ".height");     
	    title = bundle.getString("title");
	    String sum = bundle.getString("descr");
	    imgId = bundle.getInt("img");
        
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
        for (int i = 0; i < data.size(); i++) {
        	 View v = DetailListAdapter.getView(data.get(i), this);
             listView.addView(v);
        }


	    mTitleView.setText(title);
	    mSum.setText(sum);
	    mImageView.setImageResource(imgId);
	    mNavigationTitle.setText(title);
	    
	    mNavigationBackBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
	    });
	    
	    mShare.setOnClickListener(new OnClickListener(){
	    	@Override
			public void onClick(View arg0) {
	    		Toast.makeText(TransitionDetailActivity.this, "Clicked Share", Toast.LENGTH_SHORT).show();
			}
	    });
	      
	    tab.setOnCheckedChangeListener(new OnCheckedChangeListener()
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
        items.add(new ListItem(title, lor2, null, null));
        items.add(new ListItem(title, lor1, null, null));
        if (imgId != R.drawable.ph_1)
        	items.add(new ListItem("Henry Smith", "<font color='"+ getResources().getColor(R.color.theme_color) +"'>@" + title + "</font> " + lor3, null, null));
        else 
        	items.add(new ListItem("Ella Smith", "<font color='"+ getResources().getColor(R.color.theme_color) +"'>@" + title + "</font> " + lor3, null, null));
        items.add(new ListItem(title, lor2, null, null));
        if (imgId != R.drawable.ph_3)
        	items.add(new ListItem("Olivier Smith", "<font color='"+ getResources().getColor(R.color.theme_color) +"'>@" + title + "</font> " + lor4, null, null));
        else 
        	items.add(new ListItem("Ella Smith", "<font color='"+ getResources().getColor(R.color.theme_color) +"'>@" + title + "</font> " + lor3, null, null));
        items.add(new ListItem(title, lor3, null, null));
        items.add(new ListItem(title, lor4, null, null));
 
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
              
            Animation animationFadeIn = AnimationUtils.loadAnimation(TransitionDetailActivity.this,R.anim.fadein);
            Animation animationFadeOut = AnimationUtils.loadAnimation(TransitionDetailActivity.this,R.anim.fadeout);


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