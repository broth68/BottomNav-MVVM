package com.android.mvvm_bottom_nav.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.AndroidSupportInjection;

import com.android.mvvm_bottom_nav.Card;
import com.android.mvvm_bottom_nav.R;
import com.android.mvvm_bottom_nav.di.ViewModelFactory;

import javax.inject.Inject;

public class DashboardFragment extends Fragment implements CardListAdapter.OnItemClickListener {

    @Inject
    public ViewModelFactory viewModelFactory;

    private DashboardViewModel dashboardViewModel;

    private CardListAdapter cardListAdapter;
    private RecyclerView recyclerView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AndroidSupportInjection.inject(this);

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardViewModel.class);

        dashboardViewModel.getCards().observe(this, cards -> {
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

    }

    @Override
    public void onItemClick(Card item) {
        Toast.makeText(getContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
    }
}