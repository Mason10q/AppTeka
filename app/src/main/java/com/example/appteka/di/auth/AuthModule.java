package com.example.appteka.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.appteka.core.ViewModelKey;
import com.example.appteka.model.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    ViewModel bindMainActivityVM(AuthViewModel mainActivityVM);

}
