package com.android.mvvm_bottom_nav.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

        ProgressBar progressBar = binding.progressBarHome;

        binding.buttonAdd.setOnClickListener(v -> {
            if (!(binding.editTextTitle.getText().toString()).trim().isEmpty()) {
                homeViewModel.insert(new Book(binding.editTextTitle.getText().toString()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        showToast("The book is added.");
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast("The adding failed. Please retry.");
                        progressBar.setVisibility(View.GONE);
                    }
                });
                binding.editTextTitle.setText("");
                UiHelper.hideKeyboard(v);
            } else {
                Toast.makeText(getContext(), "Error. Please define a valid title.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonClear.setOnClickListener(v -> homeViewModel.deleteAll());
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}