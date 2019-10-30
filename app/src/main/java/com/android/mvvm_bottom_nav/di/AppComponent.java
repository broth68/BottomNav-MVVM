package com.android.mvvm_bottom_nav.di;

import android.app.Application;

import com.android.mvvm_bottom_nav.BottomNavApp;
import com.android.mvvm_bottom_nav.di.modules.AppModule;
import com.android.mvvm_bottom_nav.di.modules.MainActivityModule;
import com.android.mvvm_bottom_nav.di.modules.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class,
        RoomModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BottomNavApp app);
}
