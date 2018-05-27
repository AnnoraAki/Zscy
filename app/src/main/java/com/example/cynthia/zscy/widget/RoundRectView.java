package com.example.cynthia.zscy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.example.cynthia.zscy.R;

/**
 * if i have time i will deal with it :)
 */

public class RoundRectView extends View {
    private Paint mTextPaint;
    private Paint mRoundPaint;
    private String text;
    private int size;

    public RoundRectView(Context context) {
        this(context, null);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attr) {
        TypedArray typeArray = getContext().obtainStyledAttributes(attr, R.styleable.RoundRectView);
        text = typeArray.getString(R.styleable.RoundRectView_text);
        size = typeArray.getInt(R.styleable.RoundRectView_textSize,15);
        typeArray.recycle();
        init();
    }

    private void init(){
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(dp2px(size));
        mTextPaint.setColor(Color.parseColor("#7195FA"));

        mRoundPaint = new Paint();
        mRoundPaint.setAntiAlias(true);
        mRoundPaint.setStyle(Paint.Style.STROKE);
        mRoundPaint.setStrokeWidth(dp2px(1));
        mRoundPaint.setColor(Color.parseColor("#7195FA"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private int dp2px(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

}
