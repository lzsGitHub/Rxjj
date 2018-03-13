package com.example.r.rxjj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.r.rxjj.entity.ViewData;

import java.util.ArrayList;

/**
 * Created by r on 2018/3/5.
 */

/**
 * 百分比扇形图
 */
public class CircleView extends View {
    //色值组
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //初始角度
    private float startAngle = 0;
    //数据
    private ArrayList<ViewData> mData;
    //宽度 和 高度
    private int mWidth, mHeight;
    //画笔
    private Paint mPaint = new Paint();

    public CircleView(Context context) {
        super(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
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
        if (null == mData)
            return;
        float currentStartAngle = startAngle;//当前起始角度
        //把中心坐标移动到中心
        canvas.translate(mWidth / 2, mHeight / 2);
        //半径
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            ViewData mViewData = mData.get(i);
            mPaint.setColor(mViewData.getColor());
            canvas.drawArc(rect,currentStartAngle,mViewData.getAngle(),true,mPaint);
            currentStartAngle += mViewData.getAngle();
        }
    }

    //设置起始角度
    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }

    public void setData(ArrayList<ViewData> data) {
        mData = data;
        initData(data);
        invalidate();
    }

    private void initData(ArrayList<ViewData> data) {
        if (null == data || data.size() == 0)//数据异常，返回
            return;
        //总数值
        float sumValue = 0;
        for (int i = 0; i < data.size(); i++) {
            ViewData mData = data.get(i);
            //数值的和
            sumValue += mData.getValue();
            int j = i % mColors.length;
            mData.setColor(mColors[j]);
        }
        //总角度
        float sumAngle = 0;
        for (int i = 0; i < data.size(); i++) {
            ViewData mData = data.get(i);
            //角度的和
            float percentage = mData.getValue() / sumValue;//百分比
            float angle = percentage * 360;
            mData.setPercentage(percentage);
            mData.setAngle(angle);
            sumAngle += angle;
            Log.e("角度", sumAngle + "@");
        }
    }
}
