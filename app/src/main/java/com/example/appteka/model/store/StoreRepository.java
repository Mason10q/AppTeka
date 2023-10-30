package com.example.appteka.model.store;

import com.example.appteka.entities.Drug;
import com.example.appteka.entities.User;
import com.example.appteka.network.Api;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StoreRepository {

    private Api api;
    @Inject StoreRepository(Api api){
        this.api = api;
    }

    public @NonNull Single<List<Drug>> getAllDrugs(){
        return api.getAllDrugs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public @NonNull Single<Drug> deleteDrug(Drug drug){
        return api.deleteDrug(drug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public @NonNull Single<User> getUserByEmail(String email){
        return api.getUserByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public @NonNull Single<Boolean> checkIfInBasket(String drugName, String email){
        return api.checkIfInBasket(drugName, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public @NonNull Completable addToBasket(String drug_name, String user_email){
        return api.addToBasket(drug_name, user_email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
