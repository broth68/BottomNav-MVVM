package com.android.mvvm_bottom_nav.helpers;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeHelper {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";
    public static final String BATTERY_SAVER_MODE = "battery_saver";
    public static final String DEFAULT = "default";

    public static void applyTheme(String theme) {
        int mode;
        switch (theme) {
            case LIGHT_MODE :
                mode = AppCompatDelegate.MODE_NIGHT_NO;
                break;
            case DARK_MODE :
                mode = AppCompatDelegate.MODE_NIGHT_YES;
                break;
            case BATTERY_SAVER_MODE :
                mode = AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;
                break;
            default :
                mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
        }
        AppCompatDelegate.setDefaultNightMode(mode);
    }
}
