package com.example.appteka.di.auth;

import com.example.appteka.core.CoreModule;
import com.example.appteka.network.NetworkModule;
import com.example.appteka.ui.auth.SignInFragment;
import com.example.appteka.ui.auth.SignUpFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AuthModule.class, CoreModule.class})
public interface AuthComponent {

    void inject(SignInFragment fragment);
    void inject(SignUpFragment fragment);

    @Component.Builder
    interface Builder{
        AuthComponent build();
    }

}
