package com.example.r.rxjj.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.r.rxjj.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class BannerActivity extends AppCompatActivity {

    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        String[] images= getResources().getStringArray(R.array.url);
        String[] titles = getResources().getStringArray(R.array.title);
        List<?> list = Arrays.asList(images);
        List<?> arrayList = new ArrayList<>(list);
        banner=(Banner) findViewById(R.id.banner);

        //设置轮播图宽度和高度，建议最好按照图片的比例设置，效果更好
        banner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getScreenH(this)/3));


        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(arrayList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomOut);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置点击事件
        banner.setOnBannerClickListener(listener);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    OnBannerClickListener listener=new OnBannerClickListener() {
        @Override
        public void OnBannerClick(int position) {
            Toast.makeText(getApplicationContext(), "点击：" + position, Toast.LENGTH_SHORT).show();
            Log.e("--",position+"");
        }
    };

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }



    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


}
