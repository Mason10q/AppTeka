package com.example.appteka.ui.auth;

import static android.content.Context.MODE_PRIVATE;
import static com.example.appteka.core.EmailValidator.isEmailValid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appteka.ui.MainActivity;
import com.example.appteka.core.ViewModelFactory;
import com.example.appteka.databinding.FragmentSigninBinding;
import com.example.appteka.di.DaggerAuthComponent;
import com.example.appteka.entities.User;
import com.example.appteka.model.AuthViewModel;

import javax.inject.Inject;


public class     SignInFragment extends Fragment {
    private FragmentSigninBinding binding;
    @Inject ViewModelFactory factory;
    private AuthViewModel viewModel;

    private SharedPreferences sp;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        DaggerAuthComponent.builder().build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sp = getContext().getSharedPreferences("APP_PREFS", MODE_PRIVATE);

        if(!sp.getAll().isEmpty()){
            enter();
        }

        viewModel = new ViewModelProvider(this, factory).get(AuthViewModel.class);

        prepareObservers();
        binding = FragmentSigninBinding.inflate(inflater);

        binding.enterBtn.setOnClickListener(v -> enterCheck());


        return binding.getRoot();
    }

    private void prepareObservers(){
        viewModel.enterData.observe(getViewLifecycleOwner(), code -> {
            if(code == 400){
                toast("Почта не зарегестрирована");
                return;
            }

            if(code == 401){
                toast("Неверный пароль");
                return;
            }

            putEmail();
            enter();
        });

        viewModel.error.observe(getViewLifecycleOwner(), this::toast);
    }

    private void putEmail(){
        sp.edit().putString("EMAIL", binding.email.getText().toString()).apply();
    }

    private void enter(){
        startActivity(new Intent(requireActivity(), MainActivity.class));
    }

    private void enterCheck(){
        String email = binding.email.getText().toString();
        String pass = binding.password.getText().toString();

        if (email.length() == 0 || pass.length() == 0) {
            toast("Заполните поля!");
            return;
        }

        if(!isEmailValid(email)){
            toast("Почта введена неверно!");
            return;
        }

        viewModel.signIn(new User(email, pass));
    }


    private void toast(String text) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show();
    }

}