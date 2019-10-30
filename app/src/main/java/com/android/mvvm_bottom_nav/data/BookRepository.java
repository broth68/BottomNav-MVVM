package com.android.mvvm_bottom_nav.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;

@Singleton
public class BookRepository {

    private final AppExecutors appExecutors;
    private final BookDao bookDao;

    @Inject
    public BookRepository(AppExecutors appExecutors, BookDao bookDao) {
        this.appExecutors = appExecutors;
        this.bookDao = bookDao;
    }

    public LiveData<List<Book>> getBooks() {
        return bookDao.findAll();
    }

    public LiveData<Book> getBook(int id) {
        return bookDao.findById(id);
    }

    public void insert(Book book) {
        appExecutors.diskIO().execute(() -> bookDao.insertOrReplace(book));
    }

    public void delete(Book book) {
        appExecutors.diskIO().execute(() -> bookDao.delete(book));
    }

    public void deleteAll() {
        appExecutors.diskIO().execute(bookDao::deleteAll);
    }

}
