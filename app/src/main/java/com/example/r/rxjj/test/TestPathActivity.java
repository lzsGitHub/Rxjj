package com.example.r.rxjj.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.r.rxjj.R;
import com.example.r.rxjj.view.Bezier3View;
import com.example.r.rxjj.view.BezierView;

public class TestPathActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBt1;
    private Button mBt2;
    private BezierView mBz;
    private Bezier3View mBz3;
    private RadioGroup mBtRg;
    private RadioButton mKz1;
    private RadioButton mKz2;
    private Button mBt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_path);
        initView();
        setListener();
    }

    private void setListener() {
        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt3.setOnClickListener(this);
        mKz1.setOnClickListener(this);
        mKz2.setOnClickListener(this);
    }

    private void initView() {
        mBt1 = (Button) findViewById(R.id.bt_1);
        mBt2 = (Button) findViewById(R.id.bt_2);
        mBz = (BezierView) findViewById(R.id.bz);
        mBz3 = (Bezier3View) findViewById(R.id.bz_3);
        mBtRg = (RadioGroup) findViewById(R.id.bt_rg);
        mKz1 = (RadioButton) findViewById(R.id.kz_1);
        mKz2 = (RadioButton) findViewById(R.id.kz_2);
        mBt3 = (Button) findViewById(R.id.bt_3);
        mKz1.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                mBz.setVisibility(View.VISIBLE);
                mBz3.setVisibility(View.GONE);
                mBtRg.setVisibility(View.GONE);
                break;
            case R.id.bt_2:
                mBz.setVisibility(View.GONE);
                mBz3.setVisibility(View.VISIBLE);
                mBtRg.setVisibility(View.VISIBLE);
                break;
            case R.id.kz_1:
                mBz3.setMode(true);
                break;
            case R.id.kz_2:
                mBz3.setMode(false);
                break;
            case R.id.bt_3:
                startActivity(new Intent(TestPathActivity.this,HeartActivity.class));
                break;
            default:
                break;
        }
    }
}
