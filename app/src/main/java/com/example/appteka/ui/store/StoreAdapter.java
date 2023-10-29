package com.example.appteka.ui.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appteka.R;
import com.example.appteka.databinding.ItemDrugBinding;
import com.example.appteka.entities.Drug;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private ArrayList<Drug> items = new ArrayList<>();
    private DialogBinder binder;

    Picasso picasso;

    @Inject
    StoreAdapter(Picasso picasso){
        this.picasso = picasso;
    }

    public void setBinder(DialogBinder binder) {
        this.binder = binder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDrugBinding binding = ItemDrugBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot(), binding, parent.getContext());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drug item = items.get(position);

        picasso.load("http://10.0.2.2:3000/drug_images/" + item.image).into(holder.binding.drugImage);
        holder.binding.drugName.setText(item.name);
        holder.binding.drugPrice.setText(holder.context.getResources().getString(R.string.price, item.price));
        holder.binding.drugWeight.setText(holder.context.getResources().getString(R.string.weight, item.weight));

        holder.binding.getRoot().setOnClickListener(v -> {
            binder.bindDialog(item);
        });

        setAnimation(holder.itemView, holder.context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshItems(List<Drug> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    public void removeItem(Drug item){
        int pos = items.indexOf(item);
        this.items.remove(item);
        notifyItemRemoved(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, ItemDrugBinding binding, Context context) {
            super(itemView);
            this.binding = binding;
            this.context = context;
        }

        ItemDrugBinding binding;
        Context context;
    }

    private void setAnimation(View view,  Context context){
        Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }
}