package com.android.mvvm_bottom_nav.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mvvm_bottom_nav.R;
import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.di.ViewModelFactory;
import com.android.mvvm_bottom_nav.ui.UiHelper;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AndroidSupportInjection.inject(this);

        homeViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(HomeViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AutoCompleteTextView title = (AutoCompleteTextView) view.findViewById(R.id.textView_title);

        List<Book> books = new ArrayList<>();
        books.add(new Book("Lion King"));
        books.add(new Book("Cinderella"));
        books.add(new Book("Bambi"));
        books.add(new Book("Little Mermaid"));
        books.add(new Book("Robin Wood"));

        AutoCompleteBookAdapter adapter = new AutoCompleteBookAdapter(getContext(),
                R.layout.item_row_search, books);

        title.setAdapter(adapter);
        title.setOnItemClickListener((parent, view1, position, id) -> {
            title.setText(adapter.getItem(position).getTitle());
            UiHelper.hideKeyboard(getView());
        });

        EditText author = view.findViewById(R.id.textView_author);

        Button add = view.findViewById(R.id.button_add);
        add.setOnClickListener(v -> {
            if (!(title.getText().toString()).trim().isEmpty()) {
                homeViewModel.insert(new Book(title.getText().toString()));
                title.setText("");
                UiHelper.hideKeyboard(v);
            } else {
                Toast.makeText(getContext(), "Error. Please define a valid title.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button clear = view.findViewById(R.id.button_clear);
        clear.setOnClickListener(v -> homeViewModel.deleteAll());
    }
}