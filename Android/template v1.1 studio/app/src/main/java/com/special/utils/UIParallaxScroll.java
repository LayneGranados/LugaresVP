package com.special.utils;

import com.nineoldandroids.view.ViewHelper;
import com.special.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class UIParallaxScroll extends ScrollView {

    private static final boolean PRE_HONEYCOMB = Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB;
    private int mBackgroundResId;
    private int mHeaderResId;
    private View mBackgroundView;
    private View mHeaderView;
    
    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }

    private OnScrollChangedListener mOnScrollChangedListener;

    public UIParallaxScroll(Context context) {
        super(context);
        init(context, null, 0);
    }

    public UIParallaxScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public UIParallaxScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        if (isInEditMode()) {
            return;
        }

        if (attrs != null) {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.UIParallaxScroll, defStyle, 0);
            mBackgroundResId = values.getResourceId(R.styleable.UIParallaxScroll_backgroundView, 0);
            mHeaderResId = values.getResourceId(R.styleable.UIParallaxScroll_headerView, 0);
            values.recycle();
        }

        // Disable fading edge
        setVerticalFadingEdgeEnabled(false);
    }
    
    public void setHeaderView(int resId) {
        mHeaderView = findViewById(resId);
    }

    public void setHeaderView(View view) {
        mHeaderView = view;
    }

    public void setBackgroundView(int resId) {
        mBackgroundView = findViewById(resId);
    }

    public void setBackgroundView(View view) {
        mBackgroundView = view;
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            translateBackgroundView(getScrollY());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // If resource was set in XML, that child view will be available upon attaching this view
        // to the view hierarchy.
        if (mBackgroundResId > 0 && mBackgroundView == null) {
            mBackgroundView = findViewById(mBackgroundResId);
            mBackgroundResId = 0;
        }
        
        if (mHeaderResId > 0 && mHeaderView == null) {
            mHeaderView = findViewById(mHeaderResId);
            mHeaderResId = 0;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        // Clean up for versions prior to Honeycomb. Since translation is achieved using the
        // Android animation API all animations are removed when this view is detached.
        if (PRE_HONEYCOMB && mBackgroundView != null) {
            mBackgroundView.clearAnimation();
        }
        mBackgroundView = null;
        mHeaderView = null;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        translateBackgroundView(t);
        
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
    

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void translateBackgroundView(int y) {
        if (mBackgroundView != null) {
            int translationY = (int) (y * 0.5f);
            if (PRE_HONEYCOMB) {
                ViewHelper.setTranslationY(mBackgroundView, translationY);
            } else {
                mBackgroundView.setTranslationY(translationY);
            }
        }
    }
}