package com.example.appteka.network;

import com.example.appteka.entities.Drug;
import com.example.appteka.entities.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api {

    @POST("/signin")
    Single<Response<Object>> signIn(@Body User user);

    @POST("/signup")
    Single<Response<Object>> signUp(@Body User user);

    @GET("/getAllDrugs")
    Single<List<Drug>> getAllDrugs();

    @POST("/deleteDrug")
    Single<Drug> deleteDrug(@Body Drug item);

    @POST("/getUserByEmail")
    Single<User> getUserByEmail(@Query("email") String email);

    @POST("/checkIfInBasket")
    Single<Boolean> checkIfInBasket(@Query("drug_name") String drugName, @Query("email") String email);

    @POST("/addToBasket")
    Completable addToBasket(@Query("drug_name") String drugName, @Query("email") String email);

    @POST("/getAllBasket")
    Single<List<Drug>> getAllBasket(@Query("email") String email);

    @PUT("/decrease")
    Completable decrease(@Query("drug_name") String name, @Query("email") String email);

    @PUT("/increase")
    Completable increase(@Query("drug_name") String name, @Query("email") String email);

    @POST("/delete")
    Completable delete(@Query("drug_name") String name, @Query("email") String email);

    @POST("/clearBasket")
    Completable clearBasket(@Query("email") String email);
}

