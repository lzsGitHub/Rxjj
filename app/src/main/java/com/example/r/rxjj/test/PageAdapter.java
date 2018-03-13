package com.example.r.rxjj.test;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by r on 2018/1/29.
 */

public class PageAdapter extends PagerAdapter{
    /**
     * 返回当前有效视图的个数。
     * @return
     */
    @Override
    public int getCount() {
        return 0;
    }

    /**
     * 该函数用来判断instantiateItem(ViewGroup,int)函数所返回来的Key与
     * 一个页面视图是否是代表的同一个视图(即它俩是否是对应的，对应的表示同一个View)
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    /**
     * 这个方法的功能是创建指定位置的页面视图。适配器的责任就是将创建的view添加到指定的container中，
     * 返回值表示的是新增视图页面的key,一般的情况下我们将创建的视图view返回就可以了
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    /**
     * 这个方法的功能是是移除一个给定位置的页面。适配器的责任就是从容器中删除这个视图。
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
