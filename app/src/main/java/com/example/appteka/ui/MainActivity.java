package com.example.appteka.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appteka.R;
import com.example.appteka.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}