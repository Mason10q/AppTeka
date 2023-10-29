package com.example.appteka.model;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appteka.core.BaseViewModel;
import com.example.appteka.entities.User;

import java.util.HashMap;

import javax.inject.Inject;

public class AuthViewModel extends BaseViewModel {

    private MutableLiveData<Integer> _enterData = new MutableLiveData<>();
    public LiveData<Integer> enterData = _enterData;

    private AuthRepository repository;

    @Inject
    AuthViewModel(AuthRepository repository){
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void signIn(User user){
        repository.signIn(user)
                .subscribe(
                        code -> { _enterData.postValue(code); },
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void signUp(User user){
        repository.signUp(user)
                .subscribe(
                        code -> { },
                        throwable ->  _error.postValue(throwable.getMessage())
                );
    }

}
