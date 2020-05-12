package com.android.mvvm_bottom_nav.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.databinding.FragmentHomeBinding;
import com.android.mvvm_bottom_nav.di.ViewModelFactory;
import com.android.mvvm_bottom_nav.ui.UiHelper;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.AndroidSupportInjection;

public class HomeFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    private HomeViewModel homeViewModel;

    private FragmentHomeBinding binding;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AndroidSupportInjection.inject(this);

        homeViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(HomeViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAdd.setOnClickListener(v -> {
            if (!(binding.editTextTitle.getText().toString()).trim().isEmpty()) {
                homeViewModel.insert(new Book(binding.editTextTitle.getText().toString()));
                binding.editTextTitle.setText("");
                UiHelper.hideKeyboard(v);
            } else {
                Toast.makeText(getContext(), "Error. Please define a valid title.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonClear.setOnClickListener(v -> homeViewModel.deleteAll());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}