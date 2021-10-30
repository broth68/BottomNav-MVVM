package com.android.mvvm_bottom_nav.di.modules;

import android.app.Application;

import com.android.mvvm_bottom_nav.data.AppDatabase;
import com.android.mvvm_bottom_nav.data.BookDao;

import javax.inject.Singleton;

import androidx.annotation.VisibleForTesting;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class RoomModule {

    @VisibleForTesting
    public static final String DATABASE_NAME = "bottom-nav-db";

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    BookDao provideBookDao(AppDatabase db){
        return db.bookDao();
    }
}