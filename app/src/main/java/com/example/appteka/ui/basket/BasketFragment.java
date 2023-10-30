package com.example.appteka.ui.basket;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appteka.R;
import com.example.appteka.core.AndroidModule;
import com.example.appteka.core.EmptyListNotificator;
import com.example.appteka.core.ViewModelFactory;
import com.example.appteka.databinding.FragmentBasketBinding;
import com.example.appteka.di.basket.DaggerBasketComponent;
import com.example.appteka.model.basket.BasketViewModel;

import javax.inject.Inject;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;

    @Inject
    ViewModelFactory factory;

    BasketViewModel viewModel;

    @Inject
    BasketAdapter adapter;

    Boolean isProblemLayout = false;

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerBasketComponent.builder().androidModule(new AndroidModule(requireContext())).build().inject(this);

        binding = FragmentBasketBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this, factory).get(BasketViewModel.class);
        sp = requireContext().getSharedPreferences("APP_PREFS", MODE_PRIVATE);

        prepareAdapter();
        createObservers();

        viewModel.getAllBasket(sp.getString("EMAIL", " "));

        binding.payBtn.setText(getResources().getString(R.string.pay, 1));

        return binding.getRoot();
    }

    private void prepareAdapter(){
        binding.basketRecycler.setAdapter(adapter);
        adapter.setEmail(sp.getString("EMAIL", " "));
        adapter.setDrugAmountCallback(viewModel);
    }

    private void createObservers(){
        viewModel.basketData.observe(getViewLifecycleOwner(), (drugs) -> {
            adapter.refreshItems(drugs);
            setContainerView();
            binding.payBtn.setText(getResources().getString(R.string.price, adapter.countFullPrice()));
        });

        viewModel.amountChangedData.observe(getViewLifecycleOwner(), (obj) -> {
            setContainerView();
            binding.payBtn.setText(getResources().getString(R.string.price, adapter.countFullPrice()));
        });
    }

    private void setContainerView(){

        if (adapter.getItemCount() != 0 && !isProblemLayout) {
            return;
        }

        if(adapter.getItemCount() == 0){
            isProblemLayout = true;
            binding.container.removeAllViews();
            binding.container.addView(EmptyListNotificator.createEmptyListView(
                    binding.container,
                    R.drawable.ic_pharma,
                    R.string.history_empty
            ));
            binding.payBtn.setVisibility(View.INVISIBLE);
        } else{
            isProblemLayout = false;
            binding.container.removeAllViews();
            binding.container.addView(EmptyListNotificator.createRecyclerView(
                    requireContext(),
                    adapter,
                    new LinearLayoutManager(requireContext())
            ));
            binding.payBtn.setVisibility(View.VISIBLE);
        }
    }
}
