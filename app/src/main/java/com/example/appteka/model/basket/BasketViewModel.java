package com.example.appteka.model.basket;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appteka.core.BaseViewModel;
import com.example.appteka.core.SingleLiveEvent;
import com.example.appteka.entities.Drug;

import java.util.List;

import javax.inject.Inject;

public class BasketViewModel extends BaseViewModel implements DrugAmountController{

    private SingleLiveEvent<List<Drug>> _basketData = new SingleLiveEvent<>();
    public LiveData<List<Drug>> basketData = _basketData;

    private MutableLiveData<Object> _amountChangedData = new MutableLiveData<>();
    public LiveData<Object> amountChangedData = _amountChangedData;

    private BasketRepository repository;
    @Inject
    BasketViewModel(BasketRepository repository){
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllBasket(String email){
        repository.getAllBasket(email)
                .subscribe(
                        drugs -> {
                            _basketData.postValue(drugs);
                            _amountChangedData.postValue(new Object());
                        },
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void increase(String name, String email) {
        repository.increase(name, email)
                .subscribe(
                        () -> _amountChangedData.postValue(new Object()),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void decrease(String name, String email) {
        repository.decrease(name, email)
                .subscribe(
                        () -> _amountChangedData.postValue(new Object()),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void delete(String name, String email) {
        repository.delete(name, email)
                .subscribe(
                        () -> _amountChangedData.postValue(new Object()),
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void clearBasket(String email){
        repository.clearBasket(email)
                .subscribe(
                        () -> {},
                        throwable -> _error.postValue(throwable.getMessage())
                );
    }

}
