package com.android.mvvm_bottom_nav.ui.dashboard;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.data.BookRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final LiveData<List<Book>> books;

    @Inject
    public DashboardViewModel(BookRepository bookRepository) {

        this.books = bookRepository.getBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }
}