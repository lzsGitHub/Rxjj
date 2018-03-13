package com.example.r.rxjj.entity;

/**
 * Created by r on 2018/3/1.
 */

public class Test {
    private String content;
    private boolean state;

    public Test() {
    }

    public Test(String content, boolean state) {
        this.content = content;
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
