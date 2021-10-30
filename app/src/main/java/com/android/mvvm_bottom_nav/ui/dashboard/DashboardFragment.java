package com.android.mvvm_bottom_nav.ui.dashboard;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.databinding.FragmentDashboardBinding;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class DashboardFragment extends Fragment implements CardListAdapter.OnItemClickListener {

    private DashboardViewModel dashboardViewModel;

    private CardListAdapter cardListAdapter;

    private TextToSpeech textToSpeech;
    private boolean ttsAvailable = false;

    private FragmentDashboardBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardListAdapter = new CardListAdapter(getContext(), this);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardListAdapter);

        textToSpeech = new TextToSpeech(getContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.ENGLISH);

                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TextToSpeech", "Language not supported");
                } else {
                    ttsAvailable = true;
                }
            } else {
                Log.e("TextToSpeech", "Initialization failed");
            }
        });

        dashboardViewModel.getBooks().observe(getViewLifecycleOwner(),
                cards -> cardListAdapter.setData(cards));
    }

    @Override
    public void onItemClick(Book item) {
        dashboardViewModel.getBookById(item.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableMaybeObserver<Book>() {
                    @Override
                    public void onSuccess(@NonNull Book book) {
                        showToast("Emitted item: " + book.getTitle());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast("Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        showToast("Completed. No item.");
                    }
                }
        );

        if (ttsAvailable) {
            textToSpeech.speak(item.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "ttsId");
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}