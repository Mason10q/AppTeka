package com.example.appteka.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appteka.databinding.FragmentInfoBinding;
import com.example.appteka.R;

public class InfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentInfoBinding binding = FragmentInfoBinding.inflate(getLayoutInflater());

        binding.aboutAppBtn.setOnClickListener((v) -> {
            Navigation.findNavController(v).navigate(R.id.aboutAppFragment);
        });

        binding.aboutAuthorBtn.setOnClickListener((v) -> {
            Navigation.findNavController(v).navigate(R.id.aboutAuthorFragment);
        });

        binding.instructionBtn.setOnClickListener((v) -> {
            Navigation.findNavController(v).navigate(R.id.instructionFragment);
        });

        return binding.getRoot();
    }

}
