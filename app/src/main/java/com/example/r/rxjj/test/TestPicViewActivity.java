package com.example.r.rxjj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.r.rxjj.R;

public class TestPicViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pic_view);
        getBitMap();
    }

    /**
     * 几种bitmap 文件的获取方法
     */
    private void getBitMap() {
        //第一种 资源文件(drawable/mipmap/raw):
//        Bitmap bitmap =BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        //第二种 资源文件(assets)
//        Bitmap bitmap = null;
//        try {
//            InputStream inputStream = this.getAssets().open("ic_launcher.png");
//            bitmap = BitmapFactory.decodeStream(inputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //第三种  内存卡文件
//        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/ic_launcher.png");
        // 第四种 网络文件
//        Bitmap bitmap = BitmapFactory.decodeStream(is);
//        is.close();

    }

}
