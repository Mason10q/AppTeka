package com.example.appteka.di.basket;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appteka.core.ViewModelKey;
import com.example.appteka.model.basket.BasketViewModel;
import com.example.appteka.ui.basket.BasketAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface BasketModule {

    @Binds
    @IntoMap
    @ViewModelKey(BasketViewModel.class)
    ViewModel bindBasketViewModel(BasketViewModel viewModel);

    @Binds
    RecyclerView.Adapter bindBasketAdapter(BasketAdapter adapter);

}
