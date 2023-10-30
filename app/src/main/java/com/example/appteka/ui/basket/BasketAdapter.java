package com.example.appteka.ui.basket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appteka.R;
import com.example.appteka.databinding.ItemBasketBinding;
import com.example.appteka.entities.Drug;
import com.example.appteka.model.basket.DrugAmountController;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private ArrayList<Drug> items = new ArrayList<>();

    private String email;

    private DrugAmountController controller = new DrugAmountController() {
        @Override
        public void increase(String name, String email) {}

        @Override
        public void decrease(String name, String email) {}

        @Override
        public void delete(String name, String email) {}
    };

    Picasso picasso;

    @Inject
    BasketAdapter(Picasso picasso){
        this.picasso = picasso;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBasketBinding binding = ItemBasketBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new BasketAdapter.ViewHolder(binding.getRoot(), binding, parent.getContext());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BasketAdapter.ViewHolder holder, int position) {
        Drug item = items.get(position);

        picasso.load("http://10.0.2.2:3000/drug_images/" + item.image).into(holder.binding.drugImage);
        holder.binding.drugName.setText(item.name);
        holder.binding.drugPrice.setText(holder.context.getResources().getString(R.string.price, item.price));
        holder.binding.drugWeight.setText(holder.context.getResources().getString(R.string.weight, item.weight));
        holder.binding.amountCounter.setText(item.amount.toString());

        holder.binding.increaseBtn.setOnClickListener((v) -> {
            controller.increase(item.name, email);
            item.amount++;
            holder.binding.amountCounter.setText(item.amount.toString());
        });

        holder.binding.decreaseBtn.setOnClickListener((v) -> {
            if(--item.amount != 0){
                controller.decrease(item.name, email);
                holder.binding.amountCounter.setText(item.amount.toString());
            } else {
                controller.delete(item.name, email);
                removeItem(item);
            }
        });

        setAnimation(holder.itemView, holder.context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeAllItems(){
        int end = getItemCount();
        items.clear();
        notifyItemRangeRemoved(0, end);
    }

    public void removeItem(Drug item){
        int pos = items.indexOf(item);
        this.items.remove(item);
        notifyItemRemoved(pos);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshItems(List<Drug> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setDrugAmountCallback(DrugAmountController controller){
        this.controller = controller;
    }

    public Integer countFullPrice(){
        int sum = 0;

        for (Drug item : items){
            sum += (item.amount * item.price);
        }

        return sum;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        ItemBasketBinding binding;
        Context context;
        public ViewHolder(@NonNull View itemView, ItemBasketBinding binding, Context context) {
            super(itemView);
            this.binding = binding;
            this.context = context;
        }
    }

    private void setAnimation(View view,  Context context){
        Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }
}