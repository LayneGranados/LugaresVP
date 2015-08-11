package com.special.menu;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.special.R;

import java.util.ArrayList;
import java.util.List;

public class ResideMenu extends FrameLayout{

	//Statics and Constants
    public  static final int DIRECTION_LEFT  = 0;
    public  static final int DIRECTION_RIGHT = 1;
    private static final int PRESSED_MOVE_HORIZANTAL = 2;
    private static final int PRESSED_DOWN = 3;
    private static final int PRESSED_DONE = 4;
    private static final int PRESSED_MOVE_VERTICAL = 5;
    private static final int ANIM_DURATION = 250;

    //Views;
    private ImageView imageViewShadow;
    private ImageView imageViewBackground;
    private LinearLayout layoutLeftMenu;
    private ScrollView scrollViewMenu;
    private View headerView;
    /** the activity that view attach to */
    private Activity activity;
    /** the decorview of the activity    */
    private ViewGroup viewDecor;
    /** the viewgroup of the activity    */
    private TouchDisableView viewActivity;
    
    private boolean isOpened;
    private boolean showShadow;
    private float shadowAdjustScaleX;
    private float shadowAdjustScaleY;
    /** the view which don't want to intercept touch event */
    private List<View> ignoredViews;
    private List<ResideMenuItem> menuItems;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private OnMenuListener menuListener;
    private float lastRawX;
    private boolean isInIgnoredView = false;
    private int scaleDirection = DIRECTION_LEFT;
    private int pressedState   = PRESSED_DOWN;
    private List<Integer> disabledSwipeDirection = new ArrayList<Integer>();
    //valid scale factor is between 0.0f and 1.0f.
    private float mScaleValue = 0.5f;

    public ResideMenu(Context context) {
        super(context);
        initViews(context);
    }

    private void initViews(Context context){
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_menu, this);
        scrollViewMenu = (ScrollView) findViewById(R.id.sv_left_menu); //change to sv_right_menu for right
        imageViewShadow = (ImageView) findViewById(R.id.iv_shadow);
        layoutLeftMenu = (LinearLayout) findViewById(R.id.layout_left_menu); //change to layout_right_menu for right
        imageViewBackground = (ImageView) findViewById(R.id.iv_background);
    }

    /**
     * use the method to set up the activity which residemenu need to show;
     *
     * @param activity
     */
    public void attachToActivity(Activity activity){
        initValue(activity);
        setShadowAdjustScaleXByOrientation();
        viewDecor.addView(this, 0);
        setViewPadding();
    }

    private void initValue(Activity activity){
        this.activity   = activity;
        menuItems   = new ArrayList<ResideMenuItem>();
        ignoredViews    = new ArrayList<View>();
        viewDecor = (ViewGroup) activity.getWindow().getDecorView();
        viewActivity = new TouchDisableView(this.activity);

        View mContent   = viewDecor.getChildAt(0);
        viewDecor.removeViewAt(0);
        viewActivity.setContent(mContent);
        addView(viewActivity);
    }

    private void setShadowAdjustScaleXByOrientation(){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            shadowAdjustScaleX = 0.034f;
            shadowAdjustScaleY = 0.12f;
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            shadowAdjustScaleX = 0.06f;
            shadowAdjustScaleY = 0.07f;
        }
    }

    /**
     * set the menu background picture;
     *
     * @param imageResrouce
     */
    public void setBackground(int imageResrouce){
        imageViewBackground.setImageResource(imageResrouce);
    }
    
    public void setHeaderView(View v){
        headerView = v;
        ViewHelper.setAlpha(headerView, (1 - 1f) * 2.0f);
    }


    /**
     * the visiblity of shadow under the activity view;
     *
     * @param isVisible
     */
    public void setShadowVisible(boolean isVisible){
        showShadow = isVisible;
    }

    /**
     * add a single items to menu;
     *
     * @param menuItem
     */
    public void addMenuItem(ResideMenuItem menuItem){
        this.menuItems.add(menuItem);
        layoutLeftMenu.addView(menuItem);
    }

    /**
     * set the menu items by array list to menu;
     *
     * @param menuItems
     */
    public void setMenuItems(List<ResideMenuItem> menuItems){
        this.menuItems = menuItems;
        rebuildMenu();
    }

    private void rebuildMenu(){
        layoutLeftMenu.removeAllViews();
        for(int i = 0; i < menuItems.size() ; i ++)
            layoutLeftMenu.addView(menuItems.get(i), i);
    }

    /**
     * get the left menu items;
     *
     * @return
     */
    public List<ResideMenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * if you need to do something on the action of closing or opening
     * menu, set the listener here.
     *
     * @return
     */
    public void setMenuListener(OnMenuListener menuListener) {
        this.menuListener = menuListener;
    }


    public OnMenuListener getMenuListener() {
        return menuListener;
    }

    /**
     * we need the call the method before the menu show, because the
     * padding of activity can't get at the moment of onCreateView();
     */
    private void setViewPadding(){
        this.setPadding(viewActivity.getPaddingLeft(),
                viewActivity.getPaddingTop(),
                viewActivity.getPaddingRight(),
                viewActivity.getPaddingBottom());
    }

    /**
     * show the reside menu;
     */
    public void openMenu(){
        setScaleDirection(ResideMenu.DIRECTION_LEFT);
        
        if (showShadow && imageViewShadow != null){
            imageViewShadow.setImageResource(R.drawable.shadow);
        }

        isOpened = true;
        AnimatorSet scaleDown_activity = buildScaleDownAnimation(viewActivity, mScaleValue, mScaleValue);
        AnimatorSet scaleDown_shadow = buildScaleDownAnimation(imageViewShadow,
        		mScaleValue + shadowAdjustScaleX, mScaleValue + shadowAdjustScaleY);
        AnimatorSet alpha_menu = buildMenuAnimation(scrollViewMenu, 1.0f);
        AnimatorSet alpha_shadow = buildMenuAnimation(imageViewShadow, 1.0f);
        AnimatorSet alpha_header = buildMenuAnimation(headerView, 1.0f);
        AnimatorSet background_zoom = buildBackgroundAnimation(imageViewBackground, 2.0f);
        scaleDown_shadow.addListener(animationListener);
        scaleDown_activity.playTogether(scaleDown_shadow);
        scaleDown_activity.playTogether(alpha_menu);
        scaleDown_activity.playTogether(alpha_shadow);
        scaleDown_activity.playTogether(alpha_header);
        scaleDown_activity.playTogether(background_zoom);
        scaleDown_activity.start();
    }

    /**
     * close the reslide menu;
     */
    public void closeMenu(){

        isOpened = false;
        AnimatorSet scaleUp_activity = buildScaleUpAnimation(viewActivity, 1.0f, 1.0f);
        AnimatorSet scaleUp_shadow = buildScaleUpAnimation(imageViewShadow, 1.0f, 1.0f);
        AnimatorSet alpha_menu = buildMenuAnimation(scrollViewMenu, 0.0f);
        AnimatorSet alpha_shadow = buildMenuAnimation(imageViewShadow, 0.0f);
        AnimatorSet alpha_header = buildMenuAnimation(headerView, 0.0f);
        AnimatorSet background_zoom = buildBackgroundAnimation(imageViewBackground, 1.0f);
        scaleUp_activity.addListener(animationListener);
        scaleUp_activity.playTogether(scaleUp_shadow);
        scaleUp_activity.playTogether(alpha_menu);
        scaleUp_activity.playTogether(alpha_shadow);
        scaleUp_activity.playTogether(alpha_header);
        scaleUp_activity.playTogether(background_zoom);
        scaleUp_activity.start();
    }

    @Deprecated
    public void setDirectionDisable(int direction){
        disabledSwipeDirection.add(direction);
    }

    public void setSwipeDirectionDisable(int direction){
        disabledSwipeDirection.add(direction);
    }

	@SuppressWarnings("unused")
	private boolean isInDisableDirection(int direction){
        return disabledSwipeDirection.contains(direction);
    }

    private void setScaleDirection(int direction){

        int screenWidth = getScreenWidth();
        float pivotX;
        float pivotY = getScreenHeight() * 0.5f;

        pivotX  = screenWidth * 1.5f;

        ViewHelper.setPivotX(viewActivity, pivotX);
        ViewHelper.setPivotY(viewActivity, pivotY);
        ViewHelper.setPivotX(imageViewShadow, pivotX);
        ViewHelper.setPivotY(imageViewShadow, pivotY);
        scaleDirection = direction;
    }

    /**
     * return the flag of menu status;
     *
     * @return
     */
    public boolean isOpened() {
        return isOpened;
    }

    private OnClickListener viewActivityOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOpened()) closeMenu();
        }
    };

    private Animator.AnimatorListener animationListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            if (isOpened()){
                scrollViewMenu.setVisibility(VISIBLE);
                if (menuListener != null)
                    menuListener.openMenu();
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            // reset the view;
            if(isOpened()){
                viewActivity.setTouchDisable(true);
                viewActivity.setOnClickListener(viewActivityOnClickListener);
            }else{
                viewActivity.setTouchDisable(false);
                viewActivity.setOnClickListener(null);
                scrollViewMenu.setVisibility(GONE);
                if (menuListener != null)
                    menuListener.closeMenu();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    /**
     * a helper method to build scale down animation;
     *
     * @param target
     * @param targetScaleX
     * @param targetScaleY
     * @return
     */
    private AnimatorSet buildScaleDownAnimation(View target,float targetScaleX,float targetScaleY){

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", targetScaleX),
                ObjectAnimator.ofFloat(target, "scaleY", targetScaleY)
                //ObjectAnimator.ofFloat(target, "rotationY", -10)
        );
        
        scaleDown.setInterpolator(AnimationUtils.loadInterpolator(activity,
                android.R.anim.decelerate_interpolator));
        scaleDown.setDuration(ANIM_DURATION);
        return scaleDown;
    }

    /**
     * a helper method to build scale up animation;
     *
     * @param target
     * @param targetScaleX
     * @param targetScaleY
     * @return
     */
    private AnimatorSet buildScaleUpAnimation(View target,float targetScaleX,float targetScaleY){

        AnimatorSet scaleUp = new AnimatorSet();
        scaleUp.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", targetScaleX),
                ObjectAnimator.ofFloat(target, "scaleY", targetScaleY),
                ObjectAnimator.ofFloat(target, "rotationY", 0)
        );

        scaleUp.setDuration(ANIM_DURATION);
        return scaleUp;
    }

    private AnimatorSet buildMenuAnimation(View target, float alpha){

        AnimatorSet alphaAnimation = new AnimatorSet();
        alphaAnimation.playTogether(
                ObjectAnimator.ofFloat(target, "alpha", alpha)
        );

        alphaAnimation.setDuration(ANIM_DURATION);
        return alphaAnimation;
    }
    
    private AnimatorSet buildBackgroundAnimation(View target, float endScale){
        
        AnimatorSet zoomAnimation = new AnimatorSet();
        zoomAnimation.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", endScale),
                ObjectAnimator.ofFloat(target, "scaleY", endScale)
        );

        zoomAnimation.setDuration(ANIM_DURATION);
        return zoomAnimation;
    }

    /**
     * if there ware some view you don't want reside menu
     * to intercept their touch event,you can use the method
     * to set.
     *
     * @param v
     */
    public void addIgnoredView(View v){
        ignoredViews.add(v);
    }

    /**
     * remove the view from ignored view list;
     * @param v
     */
    public void removeIgnoredView(View v){
        ignoredViews.remove(v);
    }

    /**
     * clear the ignored view list;
     */
    public void clearIgnoredViewList(){
        ignoredViews.clear();
    }

    /**
     * if the motion evnent was relative to the view
     * which in ignored view list,return true;
     *
     * @param ev
     * @return
     */
    private boolean isInIgnoredView(MotionEvent ev) {
        Rect rect = new Rect();
        for (View v : ignoredViews) {
            v.getGlobalVisibleRect(rect);
            if (rect.contains((int) ev.getX(), (int) ev.getY()))
                return true;
        }
        return false;
    }

    private void setScaleDirectionByRawX(float currentRawX){
        if (currentRawX < lastRawX)
            setScaleDirection(DIRECTION_RIGHT);
        else
            setScaleDirection(DIRECTION_LEFT);
    }

    private float getTargetScale(float currentRawX){
        float scaleFloatX = ((currentRawX - lastRawX) / getScreenWidth()) * 0.75f;
        scaleFloatX = scaleDirection == DIRECTION_RIGHT ? - scaleFloatX : scaleFloatX;

        float targetScale = ViewHelper.getScaleX(viewActivity) - scaleFloatX;
        targetScale = targetScale > 1.0f ? 1.0f : targetScale;
        targetScale = targetScale < 0.5f ? 0.5f : targetScale;
        return targetScale;
    }

    private float lastActionDownX, lastActionDownY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float currentActivityScaleX = ViewHelper.getScaleX(viewActivity);
        if (currentActivityScaleX == 1.0f)
            setScaleDirectionByRawX(ev.getRawX());

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastActionDownX = ev.getX();
                lastActionDownY = ev.getY();
                isInIgnoredView = isInIgnoredView(ev) && !isOpened();
                pressedState    = PRESSED_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
            	//TODO change if different menu side
                if (isInIgnoredView || scaleDirection == ResideMenu.DIRECTION_RIGHT)
                    break;

                if(pressedState != PRESSED_DOWN &&
                        pressedState != PRESSED_MOVE_HORIZANTAL)
                    break;

                int xOffset = (int) (ev.getX() - lastActionDownX);
                int yOffset = (int) (ev.getY() - lastActionDownY);

                if(pressedState == PRESSED_DOWN) {
                    if(yOffset > 25 || yOffset < -25) {
                        pressedState = PRESSED_MOVE_VERTICAL;
                        break;
                    }
                    if(xOffset < -50 || xOffset > 50) {
                        pressedState = PRESSED_MOVE_HORIZANTAL;
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                    }
                } else if(pressedState == PRESSED_MOVE_HORIZANTAL) {
                    if (showShadow && imageViewShadow != null){
                        imageViewShadow.setImageResource(R.drawable.shadow);
                    }
                    
                    if (currentActivityScaleX < 0.95)
                        scrollViewMenu.setVisibility(VISIBLE);

                    float targetScale = getTargetScale(ev.getRawX());
                    ViewHelper.setScaleX(viewActivity, targetScale);
                    ViewHelper.setScaleY(viewActivity, targetScale);
                    ViewHelper.setScaleX(imageViewShadow, targetScale + shadowAdjustScaleX);
                    ViewHelper.setScaleY(imageViewShadow, targetScale + shadowAdjustScaleY);
                    ViewHelper.setAlpha(imageViewShadow, (1 - targetScale) * 2.0f);
                    ViewHelper.setAlpha(headerView, (1 - targetScale) * 2.0f);
                    ViewHelper.setAlpha(scrollViewMenu, (1 - targetScale) * 2.0f);

                    float adustable = ((1 / targetScale) - 1.0f);
                    float scale = 1 / (targetScale - (0.15f * adustable));
                    ViewHelper.setScaleX(imageViewBackground, scale);
                    ViewHelper.setScaleY(imageViewBackground, scale);
                    
                    lastRawX = ev.getRawX();
                    return true;
                }

                break;

            case MotionEvent.ACTION_UP:

                if (isInIgnoredView) break;
                if (pressedState != PRESSED_MOVE_HORIZANTAL) break;

                pressedState = PRESSED_DONE;
                if (isOpened()){
                    if (currentActivityScaleX > 0.56f)
                        closeMenu();
                    else
                        openMenu();
                }else{
                    if (currentActivityScaleX < 0.94f){
                        openMenu();
                    }else{
                        closeMenu();
                    }
                }

                break;

        }
        lastRawX = ev.getRawX();
        return super.dispatchTouchEvent(ev);
    }

    public int getScreenHeight(){
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getScreenWidth(){
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    
    public void setScaleValue(float scaleValue) {
        this.mScaleValue = scaleValue;
    }

    public interface OnMenuListener{

        /**
         * the method will call on the finished time of opening menu's animation.
         */
        public void openMenu();

        /**
         * the method will call on the finished time of closing menu's animation  .
         */
        public void closeMenu();
    }

}
