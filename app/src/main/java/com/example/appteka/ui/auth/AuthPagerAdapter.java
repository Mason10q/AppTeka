package com.example.appteka.ui.auth;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appteka.ui.auth.SignInFragment;
import com.example.appteka.ui.auth.SignUpFragment;

public class AuthPagerAdapter extends FragmentStateAdapter {

    public AuthPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case (0):
                return new SignInFragment();
            case (1):
                return new SignUpFragment();
        }

        return new SignInFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
