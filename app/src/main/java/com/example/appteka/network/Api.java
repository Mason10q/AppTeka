package com.example.appteka.network;

import com.example.appteka.entities.User;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("/signin")
    Single<Response<Object>> signIn(@Body User user);

    @POST("/signup")
    Single<Response<Object>> signUp(@Body User user);

}
