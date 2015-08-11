/*
 * Originally Based On:
 * Copyright 2014 Kevin Zetterstrom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.special.utils;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.Animator;
import com.special.menu.ResideMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ListView;

public class UISwipableList extends ListView {

	//Layouts
    private int mFrontLayout;
    private int mHiddenLayout;
    private View mSwipeDownView = null;
    private View mHiddenView = null;
    private ResideMenu mResideMenu;
    
    //Vars
    private boolean mSwipePaused = false;
    private float mDownX;
    private float mSwipeMin = 1;
    private boolean mSwiping = false;
    private float mFirstX;
    private float mFirstY;
    
    private Context mContext;

    public UISwipableList(Context context) {
        super(context);
        init(context);
    }

    public UISwipableList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UISwipableList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
    	mContext = context;
        setOnScrollListener(UISwipeScrollListener());
    }
    
    public void setIgnoredViewHandler(ResideMenu resideMenu) {
    	mResideMenu = resideMenu;
    }

    public void setItemLayout(int frontLayout) {
        mFrontLayout = frontLayout;
    }

    public void setActionLayout(int hiddenLayout) {
        mHiddenLayout = hiddenLayout;
    }

    private OnScrollListener UISwipeScrollListener() {
        return new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mSwipePaused = scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
            }
        };
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float lastX = ev.getX();
        float lastY = ev.getY();
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mFirstX = lastX;
            mFirstY = lastY;

            Rect rect = new Rect();
            int childCount = getChildCount();
            int[] listViewCoords = new int[2];
            getLocationOnScreen(listViewCoords);
            int x = (int) ev.getRawX() - listViewCoords[0];
            int y = (int) ev.getRawY() - listViewCoords[1];
            View child;
            for (int i = getHeaderViewsCount(); i < childCount; i++) {
                child = getChildAt(i);
                if (child != null) {
                    child.getHitRect(rect);
                    if (rect.contains(x, y)) {
                        // if a specific swiping layout has been giving, use
                        // this to swipe.
                        if (mFrontLayout > 0) {
                            View swipingView = child.findViewById(mFrontLayout);
                            if (swipingView != null) {
                                mSwipeDownView = swipingView;
                                if (mHiddenLayout > 0) {
                                    View hiddenView = child
                                            .findViewById(mHiddenLayout);
                                    if (hiddenView != null) {
                                        mHiddenView = hiddenView;
                                    }
                                }
                                break;
                            }
                        }
                        // If no swiping layout has been found, swipe the whole
                        // child
                        mSwipeDownView = mHiddenView = child;
                        break;
                    }
                }
            }

            if (mSwipeDownView != null) {
                mDownX = ev.getRawX();
            }
            break;

        case MotionEvent.ACTION_MOVE:
            float deltaX = lastX - mFirstX;
            float deltaY = lastY - mFirstY;
            if (isSwipeHorizontal(deltaX, deltaY)) {
                return true;
            }
            break;

        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    //We Wouldn't want to recycle twice, would we? View is accesible.
    @SuppressLint({ "ClickableViewAccessibility", "Recycle" })
	@Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_UP:
            break;

        case MotionEvent.ACTION_MOVE: {
            if (mSwipePaused) {
                break;
            }

            float deltaX = ev.getRawX() - mDownX;
            float lastY = ev.getY();
            float deltaY = lastY - mFirstY;
            boolean swipeLeft = false;
            if (isSwipeHorizontal(deltaX, deltaY)
                    && isSwipeDirectionLeft(deltaX) && !mHiddenView.isEnabled()) {
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                if (Math.abs(deltaX) > mSwipeMin) {
                    mSwiping = true;
                    swipeLeft = true;
                    requestDisallowInterceptTouchEvent(true);

                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent
                            .setAction(MotionEvent.ACTION_CANCEL
                                    | (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    super.onTouchEvent(cancelEvent);
                }
            } else if (isSwipeHorizontal(deltaX, deltaY)
                    && Math.abs(deltaX) > mSwipeMin) {
                mSwiping = true;
                swipeLeft = false;
                requestDisallowInterceptTouchEvent(true);

                MotionEvent cancelEvent = MotionEvent.obtain(ev);
                cancelEvent
                        .setAction(MotionEvent.ACTION_CANCEL
                                | (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                super.onTouchEvent(cancelEvent);
            } else {
                mDownX = ev.getRawX();
                deltaX = 0;
                mSwiping = false;
            }

            if (mSwiping) {
            	try {
                slideOutView(mSwipeDownView, (int) ViewHelper.getX(mSwipeDownView),
                        swipeLeft);
            	} catch (Exception e){ e.printStackTrace(); }
                return true;
            }
            break;
        }
        }
        return super.onTouchEvent(ev);
    }

    private boolean isSwipeHorizontal(float deltaX, float deltaY) {
        if (Math.abs(deltaX) > 30 && Math.abs(deltaX) > 2 * Math.abs(deltaY)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSwipeDirectionLeft(float deltaX) {
        if (deltaX < 0) {
            return true;
        } else {
            return false;
        }
    }

    private void slideOutView(final View view, final int startPosition,
            final boolean slideLeft) {
        int toPosition = 0;
        if (slideLeft) {
            if (mHiddenView != null) {
                toPosition = -1 * mHiddenView.getMeasuredWidth();
                mHiddenView.setVisibility(View.VISIBLE);
                Animation animFadeIn = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
                mHiddenView.setAnimation(animFadeIn);
            }
        } else {
            toPosition = 0;
        }
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX",
                startPosition, toPosition);
        anim.addListener(new AnimatorListenerAdapter(){

            @Override
            public void onAnimationStart(Animator animation) {
                if (mHiddenView != null) {
                    if (!slideLeft) {
                        Animation animFadeOut = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_out);
                        mHiddenView.setAnimation(animFadeOut);
                        mHiddenView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mHiddenView != null) {
                    if (slideLeft) {
                        mHiddenView.setEnabled(true);
                        if (null != mResideMenu){
                        	mResideMenu.addIgnoredView(mHiddenView);
                        	mResideMenu.addIgnoredView(mSwipeDownView);
                        }
                    } else {
                        mHiddenView.setEnabled(false);
                        if (null != mResideMenu){
                        	mResideMenu.removeIgnoredView(mHiddenView);
                        	mResideMenu.removeIgnoredView(mSwipeDownView);
                        }
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        anim.start();
    }
    
    View currentlyStickingView;

}