package com.example.appteka.di;

import androidx.lifecycle.ViewModel;

import com.example.appteka.core.ViewModelKey;
import com.example.appteka.model.AuthRepository;
import com.example.appteka.model.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    ViewModel bindMainActivityVM(AuthViewModel mainActivityVM);
//    @Binds
//    AuthRepository bindRepository(AuthRepository repository);

}
