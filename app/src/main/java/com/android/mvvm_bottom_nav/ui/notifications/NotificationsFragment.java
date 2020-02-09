package com.android.mvvm_bottom_nav.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mvvm_bottom_nav.R;
import com.android.mvvm_bottom_nav.di.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.AndroidSupportInjection;

public class NotificationsFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    private NotificationsViewModel notificationsViewModel;

    private TextView textView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AndroidSupportInjection.inject(this);

        notificationsViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(NotificationsViewModel.class);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.text_notifications);
    }
}