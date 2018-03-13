package com.example.r.rxjj.test;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import static android.R.attr.path;

/**
 * Created by r on 2018/1/29.
 */

public class GlideImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context).load(path).into(imageView);
    }
}
