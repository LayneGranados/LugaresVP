package com.special.utils;

import com.special.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

public class UICircularImage extends ImageButton {

    private int line_width = 0;
    private int line_color = Color.BLACK;   
    private int line_color_press = Color.RED;
    private int padding = 0;
    private int mLineColor = line_color;
    private int mLineWidth = line_width;
    private int mPressLineColor = line_color_press;
    private int mPadding = padding;
    private int background_color = Color.TRANSPARENT;
    private int mBackgroundColor = background_color;
    
    private float mDrawableRadius;
    private float mBorderRadius;

    private RectF mDrawableRect = new RectF();
    private RectF mBorderRect = new RectF();
    private Matrix mMatrix = new Matrix();
    private Paint mBitmapPaint = new Paint();
    private Paint mLinePaint = new Paint();

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mWidth;
    private int mHeight;

    private boolean mReady;
    private boolean mWaiting;
    

    public UICircularImage(Context context) {
        super(context);
    }

    public UICircularImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UICircularImage(Context context, AttributeSet attrs, int DEFint) {
        super(context, attrs, DEFint);
        super.setScaleType(ScaleType.CENTER_CROP);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UICircularImage, DEFint, 0);

        mLineColor = array.getColor(R.styleable.UICircularImage_line_color, line_color);
        mLineWidth = array.getDimensionPixelSize(R.styleable.UICircularImage_line_width, line_width);
        mPadding = array.getDimensionPixelSize(R.styleable.UICircularImage_padding, padding);
        mPressLineColor = array.getColor(R.styleable.UICircularImage_line_color_press, line_color_press);
        mBackgroundColor = array.getColor(R.styleable.UICircularImage_background_color, background_color);
        
        line_color = mLineColor;
        
        array.recycle();

        mReady = true;

        if (mWaiting) {
            setup();
            mWaiting = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius, mBitmapPaint);
        if (mLineWidth != 0) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBorderRadius, mLinePaint);
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldwidth, int oldheight) {
        super.onSizeChanged(width, height, oldwidth, oldheight);
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }
    
    @SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        int maskedAction = event.getActionMasked();
        if (maskedAction == MotionEvent.ACTION_DOWN) {
        	setUsedLineColor(mPressLineColor);
        	return true;
        } else if (maskedAction == MotionEvent.ACTION_UP || maskedAction == MotionEvent.ACTION_MOVE) {
        	setUsedLineColor(line_color);
        	performClick();
        }
            
        return super.onTouchEvent(event);
    }
    
    public int getLineColor() {
        return mLineColor;
    }
    
    public int getLineWidth() {
        return mLineWidth;
    }
    

    public int getBackground(int BackgroundColor) {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int BackgroundColor) {
        if (BackgroundColor == mBackgroundColor) {
            return;
        }

        mBackgroundColor = BackgroundColor;
        setup();
    }
    
    public void setUsedLineColor(int LineColor) {
        if (LineColor == mLineColor) {
            return;
        }

        mLineColor = LineColor;
        mLinePaint.setColor(mLineColor);
        invalidate();
    }
    
    public void setLineColor(int LineColor) {
        if (LineColor == mLineColor) {
            return;
        }

        mLineColor = LineColor;
        line_color = LineColor;
        mLinePaint.setColor(mLineColor);
        invalidate();
    }
    
    
    public void setPressLineColor(int PressLineColor) {
        if (PressLineColor == mPressLineColor) {
            return;
        }
        
        mPressLineColor = PressLineColor;
    }

    public void setLineWidth(int LineWidth) {
        if (LineWidth == mLineWidth) {
            return;
        }

        mLineWidth = LineWidth;
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!mReady) {
            mWaiting = true;
            return;
        }

        if (mBitmap == null) {
            return;
        }
        
        int paddingX = mPadding;
        int paddingY = mPadding;        
        
        Bitmap paddedBitmap = Bitmap.createBitmap(
        	      mBitmap.getWidth() + paddingX,
        	      mBitmap.getHeight() + paddingY,
        	      Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(paddedBitmap);
        
        canvas.drawColor(mBackgroundColor);
        //canvas.drawARGB(0xFF, 0xFF, 0xFF, 0xFF);

        canvas.drawBitmap(
        	      mBitmap,
        	      paddingX / 2,
        	      paddingY / 2,
        	      new Paint(Paint.FILTER_BITMAP_FLAG));
        
        mBitmap = paddedBitmap;

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setAntiAlias(true);
        
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mHeight = mBitmap.getHeight();
        mWidth = mBitmap.getWidth();

        mBorderRect.set(0, 0, getWidth(), getHeight());
        mBorderRadius = Math.min((mBorderRect.height() - mLineWidth) / 2, (mBorderRect.width() - mLineWidth) / 2);

        mDrawableRect.set(mLineWidth, mLineWidth, mBorderRect.width() - mLineWidth, mBorderRect.height() - mLineWidth);
        mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);

        updateMatrix();
        invalidate();
    }

    private void updateMatrix() {
        float scale;
        float dy = 0;
        float dx = 0;

        mMatrix.set(null);

        if (mWidth * mDrawableRect.height() > mDrawableRect.width() * mHeight) {
            scale = mDrawableRect.height() / (float) mHeight;
            dx = (mDrawableRect.width() - mWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mWidth;
            dy = (mDrawableRect.height() - mHeight * scale) * 0.5f;
        }

        mMatrix.setScale(scale, scale);
        mMatrix.postTranslate((int) (dx + 0.5f) + mLineWidth, (int) (dy + 0.5f) + mLineWidth);

        mBitmapShader.setLocalMatrix(mMatrix);
    }

}