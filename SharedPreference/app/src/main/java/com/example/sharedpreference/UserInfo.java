package com.example.sharedpreference;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String mUserName;
    private int mAge;
    private String mAvatarUrl;  //头像URL
    private double weight;

    public UserInfo(String mUserName, int mAge) {
        this.mUserName = mUserName;
        this.mAge = mAge;
    }

    public String getmUserName() {
        return mUserName;
    }

    public int getmAge() {
        return mAge;
    }
}
