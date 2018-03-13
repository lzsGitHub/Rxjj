package com.example.r.rxjj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by r on 2018/3/6.
 */

public class PathView extends View{
    private Paint mPaint = new Paint();

    private float mWidth = 0;
    private float mHeight = 0;
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
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
        canvas.translate(mWidth/2,mHeight/2);
        Path path =new Path();
        path.lineTo(200,200);
        path.lineTo(200,0);
//        path.lineTo(0,0);
//        path.moveTo(-200,0);
//        path.lineTo(0,0);
        path.close();
        //参数中点的顺序很重要
        path.addRect(-200,-200,200,200, Path.Direction.CW);
        path.setLastPoint(-300,300);
        Path src =new Path();
        src.addCircle(0,0,100, Path.Direction.CW);
        path.addPath(src,0,200);
        RectF rectF = new RectF(0,0,300,300);
        path.addArc(rectF,0,270);
        RectF rectF1 = new RectF(0,0,200,200);
        path.arcTo(rectF1,0,270);
        Path dst = new Path();
        path.offset(-100,0,dst);
        canvas.drawPath(path,mPaint);
//        canvas.drawPath(dst,mPaint);
        System.out.println(path.isEmpty()+"(->path中是否包含内容");
        //path中是否包含内容

        /**
         * 判断path是否是一个矩形，如果是一个矩形的话，会将矩形的信息存放进参数rect中。
         */
        RectF rect = new RectF();
        boolean b = path.isRect(rect);
        Log.e("Rect","isRect:"+b+"| left:"+rect.left+"| top:"+rect.top+"| right:"+rect.right+"| bottom:"+rect.bottom);


    }
}
