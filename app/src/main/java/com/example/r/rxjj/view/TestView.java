package com.example.r.rxjj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by r on 2018/3/6.
 */

public class TestView extends View {
    private Paint mPaint = new Paint();
    private float mWidth = 0;
    private float mHeight = 0;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //test 1
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 50f, mPaint);

        mPaint.setColor(Color.RED);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 50f, mPaint);
        //test 2 缩放
        canvas.translate(-400, -400);  //归原点
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(0, -400, 400, 0);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.BLACK);
//        canvas.scale(0.5f, 0.5f);
        //翻转
        canvas.scale(-0.5f, -0.5f);
        canvas.drawRect(rectF, mPaint);
        /**
         * 缩放 ，位移 ,旋转 相对于坐标原点都是叠加计算的
         */
        canvas.scale(-2f, -2f);
        mPaint.setColor(Color.YELLOW);
        RectF rectF1 = new RectF(-400,-400,400,400);
        for (int i = 0; i < 20; i++) {
            canvas.drawRect(rectF1, mPaint);
            canvas.scale(0.9f, 0.9f);
        }
        //旋转
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        //(角度,中心偏移量x,中心偏移量y)
        canvas.rotate(90,0,0);
        canvas.drawRect(rectF,mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.scale(5f,5f);
        canvas.drawCircle(0,0,260,mPaint);
        canvas.drawCircle(0,0,300,mPaint);
        for (int i = 0; i < 30; i++) {
            canvas.drawLine(0,260,0,300,mPaint);
            canvas.rotate(12);
        }
    }
}
