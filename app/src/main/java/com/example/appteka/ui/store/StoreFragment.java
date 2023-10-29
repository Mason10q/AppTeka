package com.example.appteka.ui.store;


import static android.content.Context.MODE_PRIVATE;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appteka.R;
import com.example.appteka.core.AndroidModule;
import com.example.appteka.core.EmptyListNotificator;
import com.example.appteka.core.ViewModelFactory;
import com.example.appteka.databinding.FragmentStoreBinding;
import com.example.appteka.di.DaggerStoreComponent;
import com.example.appteka.entities.Drug;
import com.example.appteka.entities.User;
import com.example.appteka.model.StoreViewModel;

import java.util.List;

import javax.inject.Inject;

public class StoreFragment extends Fragment{

    FragmentStoreBinding binding;
    @Inject StoreAdapter adapter;
    @Inject
    ViewModelFactory factory;
    StoreViewModel viewModel;
    SharedPreferences sp;

    @Inject DrugDialog dialog;

    DialogBinder dialogBinder;
    DialogBinder delBinder;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStoreBinding.inflate(getLayoutInflater());

        DaggerStoreComponent.builder().androidModule(new AndroidModule(requireContext())).build().inject(this);

        sp = requireContext().getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        viewModel = new ViewModelProvider(this, factory).get(StoreViewModel.class);

        prepareBinders();
        prepareObservers();

        adapter.setBinder(dialogBinder);

        viewModel.getUserByEmail(sp.getString("EMAIL", " "));
        viewModel.getAllDrugs();

        binding.deleteDrugBtn.setOnClickListener(v -> {
            adapter.setBinder(delBinder);
        });

        binding.addDrugBtn.setOnClickListener(v -> {
            addDrugs();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            viewModel.getAllDrugs();
        });

        return binding.getRoot();
    }


    private void prepareBinders(){
        dialogBinder = (item) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("DRUG", item);
            bundle.putString("USER_EMAIL", user.email);
            dialog.setArguments(bundle);
            dialog.show(getChildFragmentManager(), "COMP_DIALOG");
        };

        delBinder = (item) -> {
            viewModel.deleteDrug(item);
            adapter.setBinder(dialogBinder);
            adapter.removeItem(item);
        };
    }


    private void setContainerView(List<Drug> comps){
        if(comps.size() == 0){
            binding.container.removeAllViews();
            binding.container.addView(EmptyListNotificator.createEmptyListView(
                    binding.container,
                    R.drawable.ic_pharma,
                    R.string.history_empty
            ));
        } else{
            binding.container.removeAllViews();
            binding.container.addView(EmptyListNotificator.createRecyclerView(
                    requireContext(),
                    adapter,
                    new GridLayoutManager(requireContext(), 2)
            ));
        }
    }


    private void prepareObservers(){
        viewModel.userData.observe(getViewLifecycleOwner(), user -> {
            this.user = user;

            if(user.isAdmin){
                binding.addDrugBtn.setVisibility(View.VISIBLE);
                binding.deleteDrugBtn.setVisibility(View.VISIBLE);
            }

        });

        viewModel.allDrugs.observe(getViewLifecycleOwner(), drug -> {
            adapter.refreshItems(drug);
            setContainerView(drug);
        });


        viewModel.error.observe(getViewLifecycleOwner(), (er) -> Toast.makeText(requireContext(), er, Toast.LENGTH_LONG).show());
    }


    private void addDrugs(){

    }

}