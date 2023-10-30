package com.example.appteka.ui.store;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appteka.R;
import com.example.appteka.core.ViewModelFactory;
import com.example.appteka.databinding.DialogDrugBinding;
import com.example.appteka.entities.Drug;
import com.example.appteka.model.store.StoreViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class DrugDialog extends DialogFragment {

    Picasso picasso;

    @Inject
    ViewModelFactory factory;

    StoreViewModel viewModel;

    @Inject
    DrugDialog(Picasso picasso){
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogDrugBinding binding = DialogDrugBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(binding.getRoot());
        viewModel = new ViewModelProvider(this, factory).get(StoreViewModel.class);

        Drug drug = (Drug) getArguments().getSerializable("DRUG");
        String email = (String) getArguments().getString("USER_EMAIL");

        viewModel.checkIfInBasket(drug.name, email);

        viewModel.checkBasket.observe(this, (inBasket) -> {
            binding.addToBasketBtn.setEnabled(!inBasket);
            if (inBasket) {
                binding.addToBasketBtn.setText("Уже в корзине");
            }
        });

        binding.addToBasketBtn.setOnClickListener((v) -> {
            viewModel.addToBasket(drug.name, email);
            dismiss();
        });

        binding.dialogDismissBtn.setOnClickListener((v) -> dismiss());

        binding.dialogDrugName.setText(drug.name);
        binding.dialogDrugPrice.setText(getResources().getString(R.string.price, drug.price));
        binding.dialogDrugWeight.setText(getResources().getString(R.string.weight, drug.weight));
        binding.dialogDrugMinAge.setText(getResources().getString(R.string.age, drug.minAge));
        picasso.load("http://10.0.2.2:3000/drug_images/" + drug.image).into(binding.dialogDrugImage);

        return builder.create();
    }

}
