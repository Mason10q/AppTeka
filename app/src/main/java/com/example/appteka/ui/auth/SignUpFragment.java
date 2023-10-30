package com.example.appteka.ui.auth;

import static com.example.appteka.core.EmailValidator.isEmailValid;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appteka.core.ViewModelFactory;
import com.example.appteka.databinding.FragmentSignupBinding;
import com.example.appteka.di.auth.DaggerAuthComponent;
import com.example.appteka.entities.User;
import com.example.appteka.model.auth.AuthViewModel;

import javax.inject.Inject;

public class SignUpFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    FragmentSignupBinding binding;
    @Inject ViewModelFactory factory;
    AuthViewModel viewModel;

    String date = "";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        DaggerAuthComponent.builder().build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this, factory).get(AuthViewModel.class);

        binding.registerBtn.setOnClickListener(v -> register());

        binding.datePickBtn.setOnClickListener(v -> new DatePickerDialog(
                        requireContext(), this, 2022, 11, 11
                ).show()
        );

        viewModel.error.observe(getViewLifecycleOwner(), this::toast);

        return binding.getRoot();
    }

    private void register() {
        String name = binding.name.getText().toString();
        String password = binding.password.getText().toString();
        String email = binding.email.getText().toString();

        if (name.length() == 0 || email.length() == 0 || password.length() == 0 || date.length() == 0) {
            toast("Заполните поля!");
            return;
        }

        if (!isEmailValid(email)) {
            toast("Почта введена неверно!");
            return;
        }

        if (!password.equals(binding.repeatPassword.getText().toString())) {
            toast("Пароли не совпадают");
            return;
        }

        viewModel.signUp(new User(name, date, email, password, false));
        toast("Успешно зарегестрирован!");
    }

    private void toast(String text) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = ("" + dayOfMonth + '.' + month + '.' + year);
        binding.datePickBtn.setText(date);
    }
}
