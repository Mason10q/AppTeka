package com.example.appteka.model.store;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appteka.core.BaseViewModel;
import com.example.appteka.core.SingleLiveEvent;
import com.example.appteka.entities.Drug;
import com.example.appteka.entities.User;

import java.util.List;

import javax.inject.Inject;

public class StoreViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> _checkBasket = new MutableLiveData<>();
    public LiveData<Boolean> checkBasket = _checkBasket;

    private MutableLiveData<User> _userData = new MutableLiveData<>();
    public LiveData<User> userData = _userData;

    private SingleLiveEvent<List<Drug>> _allDrugs = new SingleLiveEvent<>();
    public LiveData<List<Drug>> allDrugs = _allDrugs;

    private MutableLiveData<Drug> _deletedDrug = new MutableLiveData<>();
    public LiveData<Drug> deletedDrug = _deletedDrug;

    private StoreRepository repository;

    @Inject
    StoreViewModel(StoreRepository repository){
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllDrugs(){
        repository.getAllDrugs()
                .subscribe(
                        drugs -> _allDrugs.postValue(drugs),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }


    @SuppressLint("CheckResult")
    public void deleteDrug(Drug item){
        repository.deleteDrug(item)
                .subscribe(
                        drug -> _deletedDrug.postValue(drug),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getUserByEmail(String email){
        repository.getUserByEmail(email)
                .subscribe(
                        user -> _userData.postValue(user),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }


    @SuppressLint("CheckResult")
    public void checkIfInBasket(String drugName, String email){
        repository.checkIfInBasket(drugName, email)
                .subscribe(
                        isIsBasket -> _checkBasket.postValue(isIsBasket),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void addToBasket(String drug_name, String user_email){
        repository.addToBasket(drug_name, user_email)
                .subscribe(
                        () -> {},
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }
}
