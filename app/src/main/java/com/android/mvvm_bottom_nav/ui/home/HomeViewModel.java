package com.android.mvvm_bottom_nav.ui.home;

import com.android.mvvm_bottom_nav.data.Book;
import com.android.mvvm_bottom_nav.data.BookRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private BookRepository bookRepository;
    private final LiveData<List<Book>> books;

    @Inject
    public HomeViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        books = bookRepository.getBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public void insert(Book book) {
        bookRepository.insert(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }
}