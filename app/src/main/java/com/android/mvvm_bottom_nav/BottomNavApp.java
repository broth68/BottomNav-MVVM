package com.android.mvvm_bottom_nav;

import androidx.multidex.MultiDexApplication;
import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class BottomNavApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
