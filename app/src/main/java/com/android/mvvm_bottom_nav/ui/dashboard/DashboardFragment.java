package com.android.mvvm_bottom_nav.ui.dashboard;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.AndroidSupportInjection;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.R;
import com.android.mvvm_bottom_nav.di.ViewModelFactory;

import java.util.Locale;

import javax.inject.Inject;

public class DashboardFragment extends Fragment implements CardListAdapter.OnItemClickListener {

    @Inject
    public ViewModelFactory viewModelFactory;

    private DashboardViewModel dashboardViewModel;

    private CardListAdapter cardListAdapter;
    private RecyclerView recyclerView;

    private TextToSpeech textToSpeech;
    private boolean ttsAvailable = false;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AndroidSupportInjection.inject(this);

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardViewModel.class);

        dashboardViewModel.getBooks().observe(this, cards -> {
            cardListAdapter.setData(cards);
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardListAdapter = new CardListAdapter(getContext(), this);

        recyclerView = view.findViewById(R.id.recyclerView);
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
    }

    @Override
    public void onItemClick(Book item) {
        if (ttsAvailable) {
            textToSpeech.speak(item.getTitle(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onStop() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onStop();
    }
}