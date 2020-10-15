package com.android.mvvm_bottom_nav.ui.dashboard;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.data.BookRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.core.Maybe;

public class DashboardViewModel extends ViewModel {

    private final LiveData<List<Book>> books;
    private BookRepository bookRepository;

    @Inject
    public DashboardViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.books = bookRepository.getBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public Maybe<Book> getBookById(int id) {
        return bookRepository.getBookById(id);
    }
}