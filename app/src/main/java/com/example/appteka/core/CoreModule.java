package com.example.appteka.core;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

@Module
public interface CoreModule {

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);



}
