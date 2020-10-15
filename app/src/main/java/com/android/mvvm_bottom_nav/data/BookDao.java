package com.android.mvvm_bottom_nav.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface BookDao {

    @Query("SELECT * FROM book LIMIT 1")
    LiveData<Book> findFirst();

    @Query("SELECT * FROM book WHERE id LIKE :id LIMIT 1")
    LiveData<Book> findById(int id);

    @Query("SELECT * FROM book WHERE id LIKE :id LIMIT 1")
    Maybe<Book> findBookById(int id);

    @Query("SELECT * FROM book")
    LiveData<List<Book>> findAll();

    @Delete
    void delete(Book object);

    @Query("DELETE FROM book")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM book")
    long count();

    @Insert
    void insert(Book object);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertOrReplace(Book object);
}
