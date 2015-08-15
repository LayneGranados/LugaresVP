package com.special;

import com.special.menu.ResideMenu;
import com.special.menu.ResideMenuItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemElements;
    private ResideMenuItem itemList1;
    private ResideMenuItem itemList2;
  
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= 21){
        getWindow().getDecorView().setSystemUiVisibility(
        	    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        	    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        setContentView(R.layout.activity_main);
        setUpMenu();
        setPaddings();
        Intent intent = getIntent();
        String scan="";
        try{
            scan = intent.getExtras().getString("escaneado");
        }catch(Exception e){
            changeFragment(new ScanCode());
        }
        if(scan!=null){
            if(!scan.equals("")){
                TransitionListFragment fragment = new TransitionListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("idlugar", scan);
                fragment.setArguments(bundle);
                changeFragment(fragment);
            }
            else{
                changeFragment(new ScanCode());
            }
        }
        else{changeFragment(new ScanCode());}

    }


    private void setUpMenu() {
    	
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setShadowVisible(true);
        resideMenu.setHeaderView(findViewById(R.id.actionbar));
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        itemHome     = new ResideMenuItem(this, R.drawable.ic_home,     "Menu Principal");
        itemElements  = new ResideMenuItem(this, R.drawable.ic_elements_alternative,  "Elements");
        itemList1 = new ResideMenuItem(this, R.drawable.ic_list_2, "List 1");
        itemList2 = new ResideMenuItem(this, R.drawable.ic_list_1, "List 2");

        itemHome.setOnClickListener(this);
        itemElements.setOnClickListener(this);
        itemList1.setOnClickListener(this);
        itemList2.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome);
        resideMenu.addMenuItem(itemElements);
        resideMenu.addMenuItem(itemList1);
        resideMenu.addMenuItem(itemList2);

        
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            //changeFragment(new HomeFragment());
            changeFragment(new ScanCode());
        }else if (view == itemElements){
            changeFragment(new ElementsFragment());
        }else if (view == itemList1){
            changeFragment(new ListFragment());
        }else if (view == itemList2){
            changeFragment(new TransitionListFragment());
        }

        resideMenu.closeMenu();
    }

    //Example of menuListener
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() { }

        @Override
        public void closeMenu() { }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    //return the residemenu to fragments
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
    
    @Override
    public void onBackPressed() {
    	if (resideMenu.isOpened()){
    		resideMenu.closeMenu();
    	} else {
    		resideMenu.openMenu();
    	}
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setPaddings();
    }
    
    /**
     * Due to a bug in Lollipop/Material theme the navigationbar overlaps the layout.
     * We fix this by manually adding padding to our main view, and (scrollable)menu view.
     */
    private void setPaddings() {
		if (Build.VERSION.SDK_INT >= 21) {

			int bottomPadding = 0;
			int rightPadding = 0;
			// int topPadding = insets.top;
			int leftPadding = 0;

			int size = getNavBarHeight(this);
			if (size > 0 && !isTablet(this)) {
				WindowManager manager = (WindowManager) this
						.getSystemService(Context.WINDOW_SERVICE);
				switch (manager.getDefaultDisplay().getRotation()) {
				case Surface.ROTATION_90:
					rightPadding += size;
					break;
				case Surface.ROTATION_180:
					// topPadding += size;
					break;
				case Surface.ROTATION_270:
					rightPadding += size;
					break;
				default:
					bottomPadding += size;
				}
			} else if (size > 0) {
				// on tablets, the navigationbar is always at the bottom.
				bottomPadding += size;
			}

			View scrollViewMenu = findViewById(R.id.sv_left_menu);
			scrollViewMenu.setPadding(scrollViewMenu.getPaddingLeft(), 0 +
			getStatusBarHeight(),scrollViewMenu.getPaddingRight(),scrollViewMenu.getPaddingBottom());

			View menu = findViewById(R.id.menu);
			menu.setPadding(0 + leftPadding,
					menu.getPaddingTop(), 0 + rightPadding,
					0 + bottomPadding);
			View main = findViewById(R.id.main);
			main.setPadding(0 + leftPadding,
					main.getPaddingTop(), 0 + rightPadding,
					0 + bottomPadding);
		}
    }
    
    @SuppressLint("NewApi")
	public int getNavBarHeight(Context c) {
    	 int result = 0;
    	 boolean hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey(); //method is only called on API21+
    	 boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

    	 if(!hasMenuKey && !hasBackKey) {
    	     //The device has a navigation bar
    		 Resources resources = getResources();
    	 
    		 int orientation = getResources().getConfiguration().orientation;
    		 int resourceId;
    		 if (isTablet(c)){
    			 resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
    		 }  else {
    			 resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_width", "dimen", "android");	 
    		 }
         
    		 if (resourceId > 0) {
    			 return getResources().getDimensionPixelSize(resourceId);
    		 }
    	 }
         return result;
	} 
    
    private boolean isTablet(Context c) {
    	return (c.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    
    public int getStatusBarHeight() { 
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        } 
        return result;
    } 
}
