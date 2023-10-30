package com.example.appteka.model.auth;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appteka.core.BaseViewModel;
import com.example.appteka.entities.User;

import javax.inject.Inject;

public class AuthViewModel extends BaseViewModel {

    private MutableLiveData<Integer> _enterData = new MutableLiveData<>();
    public LiveData<Integer> enterData = _enterData;

    private MutableLiveData<Integer> _signUpCode = new MutableLiveData<>();
    public LiveData<Integer> signUpCode = _signUpCode;
    private AuthRepository repository;

    @Inject
    AuthViewModel(AuthRepository repository){
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void signIn(User user){
        composite.add(repository.signIn(user)
                .subscribe(
                        code -> { _enterData.postValue(code); },
                        throwable -> _error.postValue(throwable.getMessage())
                )
        );
    }

    @SuppressLint("CheckResult")
    public void signUp(User user){
        composite.add(repository.signUp(user)
                .subscribe(
                        code -> { _signUpCode.postValue(code); },
                        throwable ->  _error.postValue(throwable.getMessage())
                )
        );
    }

}
