package com.android.mvvm_bottom_nav.ui.dashboard;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.data.BookRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Maybe;

@HiltViewModel
public class DashboardViewModel extends ViewModel {

    private final LiveData<List<Book>> books;
    private final BookRepository bookRepository;
    private final SavedStateHandle savedStateHandle;

    @Inject
    public DashboardViewModel(BookRepository bookRepository, SavedStateHandle savedStateHandle) {
        this.bookRepository = bookRepository;
        this.savedStateHandle = savedStateHandle;
        this.books = bookRepository.getBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public Maybe<Book> getBookById(int id) {
        return bookRepository.getBookById(id);
    }
}