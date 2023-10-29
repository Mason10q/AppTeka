package com.example.appteka.ui.auth;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appteka.databinding.ActivityAuthBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareAdapter();
    }

    private void prepareAdapter(){
        binding.pager.setAdapter(new AuthPagerAdapter(this));
        attachMediator();
    }

    private void attachMediator(){

        new TabLayoutMediator(binding.tabs, binding.pager, (tab, position) -> {
            switch (position){
                case(0):
                    tab.setText("Вход");
                    break;
                case(1):
                    tab.setText("Регистрация");
                    break;
            }
        }).attach();
    }

}
