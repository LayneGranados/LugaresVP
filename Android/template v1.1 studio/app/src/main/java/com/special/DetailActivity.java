package com.special;

import com.special.R;
import com.special.utils.UICircularImage;
import com.special.utils.UIParallaxScroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	//Views
	LinearLayout topview;
	TextView titleview;
	TextView titleBar;
	ImageView mImg;
	
	//Layouts
	String title;
	 
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        	getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
		setContentView(R.layout.activity_detail);
        System.out.println("Entro a donde uno de los de la lista");
		((UIParallaxScroll) findViewById(R.id.scroller)).setOnScrollChangedListener(mOnScrollChangedListener);

	    topview = (LinearLayout) findViewById(R.id.layout_top);   
	    titleBar = (TextView) findViewById(R.id.titleBar);
	    
	    //Setting the titlebar background blank (initial state)
	    topview.getBackground().setAlpha(0);
	    titleBar.setVisibility(View.INVISIBLE);

        ImageButton b = null;//(UICircularImage) R.id.action1;

	    Intent intent = getIntent();
	    title = intent.getExtras().getString("title");
	    String sum = intent.getExtras().getString("descr");
	    int imgId = intent.getExtras().getInt("img");
	    
	    String descr = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras malesuada porta dignissim. Nam ut libero ullamcorper, molestie massa id, suscipit tellus. Morbi facilisis a augue non dapibus. Praesent turpis ligula, malesuada non laoreet et, tincidunt vel felis. Nam nec dui sollicitudin elit facilisis placerat in eget risus. Praesent venenatis mauris nec est ultricies, a iaculis odio fringilla. Nulla accumsan felis sed mi hendrerit, ut fringilla velit consequat."
                + "\n\n" +
	    		"Phasellus sagittis, enim ac blandit vestibulum, augue nisl malesuada dolor, nec eleifend lorem nibh imperdiet ante. In semper nunc non faucibus tristique. Nam convallis arcu imperdiet lectus aliquet elementum. Fusce convallis porta dolor vitae vehicula. Sed molestie urna quis lacus scelerisque, ac cursus nisl euismod. Vestibulum consectetur nunc ac euismod facilisis. Maecenas fringilla et dolor vitae aliquet. Proin commodo mattis fringilla. In malesuada urna sit amet eros bibendum, sed varius nisi fringilla. Proin condimentum varius tortor, scelerisque consectetur tellus suscipit eget. Nullam varius nulla eget fermentum tristique. Morbi ullamcorper ultricies semper. Etiam mollis ipsum tempus mi ultrices, nec lacinia urna convallis. Curabitur dignissim ipsum sit amet fermentum fermentum. Nunc sit amet nulla adipiscing, egestas mauris non, gravida dui. Aenean ullamcorper egestas interdum."
	    		+ "\n\n" +
	    		"Duis quis lectus nec augue cursus suscipit id adipiscing elit. Etiam ante nulla, bibendum quis volutpat a, rutrum vitae odio. Vestibulum posuere vulputate lectus eu egestas. Maecenas tincidunt iaculis nibh, ullamcorper cursus arcu pretium vitae. Donec sed facilisis leo. Sed rhoncus lectus eu orci iaculis tincidunt. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras erat felis, consequat et libero et, dapibus pulvinar augue. Nunc a arcu vel orci molestie pretium sed sit amet sapien. Mauris ac diam a mauris posuere aliquam. Morbi nec lacinia elit. Vivamus imperdiet feugiat velit, et hendrerit mi blandit id. Integer pharetra porta lorem."
	    		+ "\n\n" +
	    		"Aliquam vitae malesuada velit. Pellentesque augue nisl, euismod et lorem sed, lobortis laoreet nisl. Quisque lobortis dolor sodales nisl blandit, sit amet vestibulum felis mollis. Aenean porta libero nibh, ut iaculis tortor faucibus vel. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer diam est, sollicitudin quis nisl vitae, pulvinar vulputate massa. Quisque aliquam leo viverra nulla mattis, in tempus enim dignissim. Fusce non nisi libero. Integer volutpat dolor et enim tristique ultrices. Proin semper sagittis laoreet. Etiam consectetur dui quis mi dictum, in condimentum dui elementum. In porta sem at convallis interdum."
	    		+ "\n\n" +
	    		"Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed aliquam consequat metus non iaculis. Mauris varius euismod faucibus. Nullam vitae odio vel est adipiscing elementum quis ac ipsum. Vestibulum sit amet fringilla mauris. Duis magna est, elementum sit amet arcu vel, tempor vehicula odio. Integer ultricies, magna non eleifend tristique, nunc ligula congue nisi, sit amet adipiscing enim augue et nibh. Integer mattis urna id dignissim pretium. Fusce mauris augue, varius tempor iaculis sed, bibendum nec augue. Praesent a venenatis tortor. Nullam eu tellus elit. Maecenas pharetra risus commodo, ullamcorper mi non, porta magna. Vestibulum augue libero, tempor vitae tellus vel, aliquam luctus mi."
	    		+ "\n\n" +
	    		"The image that you are seeing above is retrieved from flickr by 'Hotel du Vin and Bistro' and shared under the Create Commons NoDerivs 2.0 License.";
	    		
	    titleview = (TextView) findViewById(R.id.title);
	    TextView mSum = (TextView) findViewById(R.id.sumary);
	    TextView mDescr = (TextView) findViewById(R.id.description);
	    mImg = (ImageView) findViewById(R.id.imageView);
	    
	    titleview.setText(title);
	    mDescr.setText(descr);
	    mSum.setText(sum);
	    mImg.setImageResource(imgId);
	    
	    titleBar.setText(title);
	    
	    Button btnback = (Button) findViewById(R.id.title_bar_left_menu);
	    btnback.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
	    	
	    });
	}
	
	@Override
	public void onStop(){
		super.onStop();
		mImg.setImageResource(0);
	}
	
	//performing changes to the titlebars visibility
	private UIParallaxScroll.OnScrollChangedListener mOnScrollChangedListener = new UIParallaxScroll.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        	//At this height, the title has to be fully visible
        	final int headerHeight = (findViewById(R.id.imageView).getHeight() + titleview.getHeight()) - topview.getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            topview.getBackground().setAlpha(newAlpha);
            topview.getBackground().setAlpha(newAlpha);
            
            Animation animationFadeIn = AnimationUtils.loadAnimation(DetailActivity.this,R.anim.fadein);
            Animation animationFadeOut = AnimationUtils.loadAnimation(DetailActivity.this,R.anim.fadeout);
            
            if (newAlpha == 255 && titleBar.getVisibility() != View.VISIBLE && !animationFadeIn.hasStarted()){
            	titleBar.setVisibility(View.VISIBLE);
            	titleBar.startAnimation(animationFadeIn);
            } else if (newAlpha < 255 && !animationFadeOut.hasStarted() && titleBar.getVisibility() != View.INVISIBLE)  { 	
            	titleBar.startAnimation(animationFadeOut);
            	titleBar.setVisibility(View.INVISIBLE);
            	
            }
        }
    };
	
	
}
