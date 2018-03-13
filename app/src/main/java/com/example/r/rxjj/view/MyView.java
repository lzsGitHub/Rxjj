package com.example.r.rxjj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by r on 2018/3/5.
 */

public class MyView extends View {
    public static final int mMeasureWidth = 10;
    public static final int mMeasureHeight = 10;
    //笔
    private Paint mPaint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * STROKE                //描边
     FILL                  //填充
     FILL_AND_STROKE       //描边加填充
     */
    private void initPaint() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);//填充
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        setMeasuredDimension(mMeasureWidth,mMeasureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawPoint(50,50,mPaint);
        canvas.drawPoints(new float[]{
                50,100,
                50,150,
                50,200,
        },mPaint);
        canvas.drawLine(60,50,120,100,mPaint);
        //type : 1
        canvas.drawRect(120,100,240,200,mPaint);
        //type : 2
        Rect rect = new Rect(240,200,360,300);
        canvas.drawRect(rect,mPaint);
        //type : 3
        RectF rectF = new RectF(360,300,480,400);
        canvas.drawRect(rectF,mPaint);
        //圆角矩形  rx ry 椭圆的rx ， ry
        RectF roundRectF = new RectF(480,400,600,500);
        canvas.drawRoundRect(roundRectF,30f,30f,mPaint);
        //椭圆
        RectF ovalRect = new RectF(600,500,720,600);
        canvas.drawOval(ovalRect,mPaint);
        //圆 坐标 x y  半径
        canvas.drawCircle(500,100,50,mPaint);
        //绘制一个圆弧
        mPaint.setColor(Color.WHITE);
        RectF arcRectf = new RectF(120,400,240,520);
        canvas.drawRect(arcRectf,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(arcRectf,30,-240,true,mPaint);
        mPaint.setColor(Color.WHITE);
        RectF inRectF = new RectF(140,420,220,500);
        canvas.drawArc(inRectF,30,-240,true,mPaint);
    }
}
