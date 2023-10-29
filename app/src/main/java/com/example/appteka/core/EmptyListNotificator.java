package com.example.appteka.core;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appteka.databinding.ProblemLayoutBinding;

public class EmptyListNotificator {

    public static <T extends RecyclerView.ViewHolder> RecyclerView createRecyclerView(
            Context context,
            RecyclerView.Adapter<T> adapter,
            RecyclerView.LayoutManager manager
    ) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return recyclerView;
    }


    public static View createEmptyListView(
            ViewGroup container,
            @DrawableRes int notifyImageId,
            @StringRes int notifyTextId
    ) {
        Context context = container.getContext();

        ProblemLayoutBinding binding = ProblemLayoutBinding.inflate(
                LayoutInflater.from(context), container, false
        );

        binding.notifyImage.setImageDrawable(AppCompatResources.getDrawable(context, notifyImageId));
        binding.notifyText.setText(context.getText(notifyTextId));

        return binding.getRoot();
    }
}
