package com.example.r.rxjj.entity;

import android.support.annotation.NonNull;

/**
 * Created by r on 2018/3/5.
 */

public class ViewData {
    private String name;
    private float value;
    private float percentage;

    private int color = 0;
    private float angle = 0;

    public ViewData(@NonNull String name,@NonNull float value) {
        this.name = name;
        this.value = value;
    }

    public ViewData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
