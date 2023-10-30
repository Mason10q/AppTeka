package com.example.appteka.model.basket;

import com.example.appteka.entities.Drug;
import com.example.appteka.network.Api;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BasketRepository {


    private Api api;
    @Inject
    BasketRepository(Api api){
        this.api = api;
    }

    public @NonNull Single<List<Drug>> getAllBasket(String email){
        return api.getAllBasket(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public @NonNull Completable increase(String name, String email) {
        return api.increase(name, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public @NonNull Completable decrease(String name, String email) {
        return api.decrease(name, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public @NonNull Completable delete(String name, String email) {
        return api.delete(name, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable clearBasket(String email){
        return api.clearBasket(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
