package com.example.appteka.di.basket;

import com.example.appteka.core.AndroidModule;
import com.example.appteka.core.CoreModule;
import com.example.appteka.network.NetworkModule;
import com.example.appteka.ui.basket.BasketFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, BasketModule.class, CoreModule.class, AndroidModule.class})
public interface BasketComponent {

    void inject(BasketFragment fragment);

    @Component.Builder
    interface Builder{
        BasketComponent build();
        Builder androidModule(AndroidModule module);
    }

}