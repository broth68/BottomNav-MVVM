package com.android.mvvm_bottom_nav.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.AndroidSupportInjection;

import com.android.mvvm_bottom_nav.R;
import com.android.mvvm_bottom_nav.di.ViewModelFactory;

import javax.inject.Inject;

public class NotificationsFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    private NotificationsViewModel notificationsViewModel;

    private TextView textView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AndroidSupportInjection.inject(this);

        notificationsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NotificationsViewModel.class);

        notificationsViewModel.getText().observe(this, s -> textView.setText(s));
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