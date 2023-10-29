package com.example.appteka.model;

import com.example.appteka.entities.User;
import com.example.appteka.network.Api;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;


public class AuthRepository {

    private Api api;
    @Inject AuthRepository(Api api){
        this.api = api;
    }

    public @NonNull Single<Integer> signUp(User user){
        return api.signUp(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(Response::code);
    }


    public @NonNull Single<Integer> signIn(User user){
        return api.signIn(user)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(Response::code);
    }

}
