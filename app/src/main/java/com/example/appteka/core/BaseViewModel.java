package com.example.appteka.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;


    protected CompositeDisposable composite = new CompositeDisposable();

    public void onStop(){
        composite.dispose();
    }

}
