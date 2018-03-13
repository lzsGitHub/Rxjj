package com.example.r.rxjj.test;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.r.rxjj.BR;
import com.example.r.rxjj.R;
import com.example.r.rxjj.databinding.ActivityDataBindingBinding;
import com.example.r.rxjj.entity.User;
import com.example.r.rxjj.listener.EventListener;

public class DataBindingActivity extends AppCompatActivity {

    private ActivityDataBindingBinding mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(DataBindingActivity.this, R.layout.activity_data_binding);
        mViewDataBinding.setContent("对String类型的数据绑定");
        mViewDataBinding.setEnabled(true);
        mViewDataBinding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DataBindingActivity.this,"点击到了",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DataBindingActivity.this,PathTestActivity.class));
            }
        });
        User user = new User();
        user.setName("Model数据类型--->名字");
        mViewDataBinding.setVariable(BR.user,user);
        mViewDataBinding.setT1("事件绑定1");
        mViewDataBinding.setT2("事件绑定2");
        mViewDataBinding.setT3("事件绑定3");
        mViewDataBinding.setEvent(new EventListener() {
            @Override
            public void click1(View v) {
                mViewDataBinding.setT1("触发了事件1");
                startActivity(new Intent(DataBindingActivity.this,TestViewActivity.class));
            }

            @Override
            public void click2(View v) {
                mViewDataBinding.setT2("触发了事件2");
                startActivity(new Intent(DataBindingActivity.this,TestPicViewActivity.class));
            }

            @Override
            public void click3(String s) {
                mViewDataBinding.setT3(s);
                startActivity(new Intent(DataBindingActivity.this,TestPathActivity.class));
            }
        });
        mViewDataBinding.btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewDataBinding.btTest.setText("按钮Test");
                startActivity(new Intent(DataBindingActivity.this,
                        ViewTestActivity.class));
            }
        });
    }
}
