package com.android.mvvm_bottom_nav.di.modules;

import com.android.mvvm_bottom_nav.di.ViewModelFactory;
import com.android.mvvm_bottom_nav.di.ViewModelKey;
import com.android.mvvm_bottom_nav.ui.dashboard.DashboardViewModel;
import com.android.mvvm_bottom_nav.ui.home.HomeViewModel;
import com.android.mvvm_bottom_nav.ui.notifications.NotificationsViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel.class)
    abstract ViewModel bindDashboardViewModel(DashboardViewModel dashboardViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel.class)
    abstract ViewModel bindFavouritesViewModel(NotificationsViewModel notificationsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}