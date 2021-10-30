package com.android.mvvm_bottom_nav.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvvm_bottom_nav.databinding.FragmentNotificationsBinding;
import com.android.mvvm_bottom_nav.helpers.ThemeHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private FragmentNotificationsBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationsViewModel = new ViewModelProvider(this)
                .get(NotificationsViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonLight.setOnClickListener(v -> ThemeHelper.applyTheme(ThemeHelper.LIGHT_MODE));

        binding.buttonDark.setOnClickListener(v -> ThemeHelper.applyTheme(ThemeHelper.DARK_MODE));

        binding.buttonDefault.setOnClickListener(v -> ThemeHelper.applyTheme(ThemeHelper.DEFAULT));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}