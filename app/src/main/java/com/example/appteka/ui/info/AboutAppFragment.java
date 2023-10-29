package com.example.appteka.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appteka.databinding.AboutAppFragmentBinding;

public class AboutAppFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AboutAppFragmentBinding binding = AboutAppFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

}
