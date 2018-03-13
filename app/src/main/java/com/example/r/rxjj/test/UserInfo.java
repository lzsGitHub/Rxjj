package com.example.r.rxjj.test;

import java.io.Serializable;

/**
 * Created by r on 2018/1/30.
 */

public class UserInfo implements Serializable{
    private String userId;
    private String imageUrl;
    private String nickname;
    private String roleName;
    private String departName;
    private String pinyin;
    private int type;
    private String phone;
    private int sex;
    private String birthday;
    private String area;
    private String roleDepartDesc;
    private int noReadCount;
    private String id;
    private int isHaveCompany;

    public int getIsHaveCompany() {
        return isHaveCompany;
    }

    public void setIsHaveCompany(int isHaveCompany) {
        this.isHaveCompany = isHaveCompany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleDepartDesc() {
        return roleDepartDesc;
    }

    public void setRoleDepartDesc(String roleDepartDesc) {
        this.roleDepartDesc = roleDepartDesc;
    }

    public int getNoReadCount() {
        return noReadCount;
    }

    public void setNoReadCount(int noReadCount) {
        this.noReadCount = noReadCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
