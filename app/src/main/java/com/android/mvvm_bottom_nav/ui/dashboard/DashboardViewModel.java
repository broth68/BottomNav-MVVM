package com.android.mvvm_bottom_nav.ui.dashboard;

import com.android.mvvm_bottom_nav.Card;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<List<Card>> dashboardData;

    @Inject
    public DashboardViewModel() {
        dashboardData = new MutableLiveData<>();

        List<Card> cards = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++) {
            cards.add(new Card("Card " + (i + 1)));
        }

        dashboardData.setValue(cards);
    }

    public LiveData<List<Card>> getCards() {
        return dashboardData;
    }
}