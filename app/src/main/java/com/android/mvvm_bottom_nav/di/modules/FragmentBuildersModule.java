package com.android.mvvm_bottom_nav.di.modules;

import com.android.mvvm_bottom_nav.ui.dashboard.DashboardFragment;
import com.android.mvvm_bottom_nav.ui.home.HomeFragment;
import com.android.mvvm_bottom_nav.ui.notifications.NotificationsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract DashboardFragment contributeDashboardTabsFragment();

    @ContributesAndroidInjector
    abstract NotificationsFragment contributeNotificationsFragment();
}