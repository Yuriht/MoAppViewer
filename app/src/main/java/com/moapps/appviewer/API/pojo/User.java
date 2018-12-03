package com.moapps.appviewer.API.pojo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userNick")
    public String userNick ;
    @SerializedName("password")
    public String password;
    @SerializedName("data")
    public String data;
    @SerializedName("err")
    public Boolean err;
    @SerializedName("code")
    public int code;

    public User(String userNick, String password) {
        this.userNick = userNick;
        this.password = password;
    }


}