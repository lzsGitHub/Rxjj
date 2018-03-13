package com.example.r.rxjj.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.r.rxjj.R;

/**
 * Created by r on 2018/3/6.
 */

public class PictureView extends View {
    private Picture mPicture = new Picture();
    private Paint mPaint = new Paint();
    private Context mContext;
    private float mWidth = 0;
    private float mHeight = 0;
    private String str="乾坤巽震坎离艮兑";
    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        recording();
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
        //1、picture 的draw方法
        /**
         * 这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用。
         */
//        mPicture.draw(canvas);
        //2、canvas的draw方法
//        canvas.drawPicture(mPicture);
//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),mPicture.getHeight()));
        //3、封装成PictureDrawable再绘制的方法
        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
        //设置绘制区域
        pictureDrawable.setBounds(0,0,250,mPicture.getHeight());
        pictureDrawable.draw(canvas);
        /**
         * 画BitMap
         */
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,new Matrix(),new Paint());//默认
        canvas.drawBitmap(bitmap,200,500,new Paint());

        canvas.translate(mWidth/2,mHeight/2);
        //指定图片绘制区域
        Rect src = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight()/2);
        //指定显示区域  全占满
        Rect dst = new Rect(0,0,200,400);
        canvas.drawBitmap(bitmap,src,dst,new Paint());

        /**
         *画文字
         */
        //1、基本指定基线
        mPaint.setTextSize(50f);
        canvas.drawText(str,0,0,mPaint);
        canvas.drawText(str,0,4,-100,100,mPaint);
        char[] chars = str.toCharArray();
        canvas.drawText(chars,1,7,-200,200,mPaint);
        //2、给每个字符指定一个位置的方法
        canvas.restore();
//        canvas.drawPosText(str,new float[]{
//                100,100,    // 第一个字符位置
//                200,200,    // 第二个字符位置
//                300,300,    // ...
//                400,400,
//                500,500,
//
//        },mPaint);
    }
    //picture的录制方法
    public void recording(){
        Canvas canvas = mPicture.beginRecording(500,500);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        //在画布上的具体操作
        canvas.drawCircle(200,200,100f,mPaint);
        mPicture.endRecording();
    }
}
