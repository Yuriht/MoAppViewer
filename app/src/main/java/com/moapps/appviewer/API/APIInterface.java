package com.moapps.appviewer.API;

import com.moapps.appviewer.API.pojo.AppList;
import com.moapps.appviewer.API.pojo.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/Account/Login")
    Call<User> createUser(@Body User user);

    @POST("api/application")
    Call<AppList> createAppList(@Body AppList app_list);

}