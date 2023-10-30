package com.example.appteka.di.store;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appteka.core.ViewModelKey;
import com.example.appteka.model.store.StoreViewModel;
import com.example.appteka.ui.store.StoreAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface StoreModule {

    @Binds
    @IntoMap
    @ViewModelKey(StoreViewModel.class)
    ViewModel bindStoreViewModel(StoreViewModel viewModel);

    @Binds
    RecyclerView.Adapter bindStoreAdapter(StoreAdapter adapter);

}
