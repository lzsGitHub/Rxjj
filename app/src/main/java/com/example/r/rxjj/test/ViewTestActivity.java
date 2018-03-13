package com.example.r.rxjj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.r.rxjj.R;
import com.example.r.rxjj.entity.ViewData;
import com.example.r.rxjj.view.CircleView;

import java.util.ArrayList;

public class ViewTestActivity extends AppCompatActivity {
    private CircleView mMyCircleView;
    private ArrayList<ViewData> mViewDatas =new ArrayList<ViewData>();
    private float[] mValue = {50,100,38,10,6,200};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        mMyCircleView = (CircleView) findViewById(R.id.my_circle_view);
        mMyCircleView.setStartAngle(0f);
        initViewData();
    }

    private void initViewData() {
        for (int i = 0; i < mValue.length; i++) {
             ViewData mVd = new ViewData();
             mVd.setValue(mValue[i]);
            mViewDatas.add(mVd);
        }
        System.out.println(mViewDatas.size()+"dadadadadadda");
        mMyCircleView.setData(mViewDatas);
    }
}
