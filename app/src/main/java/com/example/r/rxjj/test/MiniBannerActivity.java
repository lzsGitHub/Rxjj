package com.example.r.rxjj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.r.rxjj.R;
import com.sivin.Banner;

public class MiniBannerActivity extends AppCompatActivity {
    private Banner mIdBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_banner);

        mIdBanner = (Banner) findViewById(R.id.id_banner);
//        BannerAdapter adapter = new BannerAdapter<BannerModel>(mDatas) {
//            @Override
//            protected void bindTips(TextView tv, BannerModel bannerModel) {
//                tv.setText(bannerModel.getTips());
//            }
//            @Override
//            public void bindImage(ImageView imageView, BannerModel bannerModel) {
//                Glide.with(mContext)
//                        .load(bannerModel
//                                .getImageUrl())
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.mipmap.ic_launcher)
//                        .into(imageView);
//            }
//        };
//        mIdBanner.setBannerAdapter(adapter);

    }
}
