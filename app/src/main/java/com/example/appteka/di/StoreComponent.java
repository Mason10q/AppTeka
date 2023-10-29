package com.example.appteka.di;

import com.example.appteka.core.AndroidModule;
import com.example.appteka.core.CoreModule;
import com.example.appteka.network.NetworkModule;
import com.example.appteka.ui.store.StoreFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, StoreModule.class, CoreModule.class, AndroidModule.class})
public interface StoreComponent {

    void inject(StoreFragment fragment);

    @Component.Builder
    interface Builder{
        StoreComponent build();
        Builder androidModule(AndroidModule module);
    }

}