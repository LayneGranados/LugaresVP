package com.special.utils;

import com.special.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioGroup;

public class UITabs extends RadioGroup {

    private int mDPvalue;
    private Resources mResources;

    public UITabs(Context context) {
        super(context);
        mResources = getResources();
        mDPvalue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, mResources.getDisplayMetrics());
    }
    
    public UITabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        mResources = getResources();
        mDPvalue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, mResources.getDisplayMetrics());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateBackground();
    }


    public void updateBackground() {
        int count = super.getChildCount();
        if (count > 1) {
            View mChild = getChildAt(0);
            
            LayoutParams initParams = (LayoutParams) mChild.getLayoutParams();
            LayoutParams params = new LayoutParams(initParams.width, initParams.height, initParams.weight);
            params.setMargins(0, 0, -mDPvalue, 0);
            mChild.setLayoutParams(params);
            
            createBackground(getChildAt(0), R.drawable.tab_active_left, R.drawable.tab_left);
            
            for (int i = 1; i < count - 1; i++) {
                createBackground(getChildAt(i), R.drawable.tab_active_middle, R.drawable.tab_middle);
                View mChildSecondary = getChildAt(i);
                
                initParams = (LayoutParams) mChildSecondary.getLayoutParams();
                params = new LayoutParams(initParams.width, initParams.height, initParams.weight);
                params.setMargins(0, 0, -mDPvalue, 0);
                mChildSecondary.setLayoutParams(params);
            }
            createBackground(getChildAt(count - 1), R.drawable.tab_active_right, R.drawable.tab_right);
        } else if (count == 1) {
            createBackground(getChildAt(0), R.drawable.tab_active_default, R.drawable.tab_default);
        }
    }

    @SuppressWarnings("deprecation")
	@SuppressLint("NewApi") 
    private void createBackground(View view, int checked, int unchecked) {
    	Drawable mCheckedDrawable = mResources.getDrawable(checked).mutate();
        Drawable mUncheckedDrawable = mResources.getDrawable(unchecked).mutate();
         
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, mUncheckedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, mCheckedDrawable);

        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }
}