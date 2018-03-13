package com.example.r.rxjj.testEntity;

import android.util.Log;

/**
 * Created by r on 2018/2/1.
 */

public class Translation1 {
    private int status;
    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public String show() {

        Log.d("RxJava", "翻译内容 = " + content.out);

        return "翻译内容 = " + content.out;
    }
}
