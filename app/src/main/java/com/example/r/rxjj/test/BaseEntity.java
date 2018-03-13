package com.example.r.rxjj.test;

import java.io.Serializable;

/**
 * Created by r on 2018/1/30.
 */

public class BaseEntity<T> implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -7286636832971149907L;

    private int code;
    private String desc;
    private T data;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
