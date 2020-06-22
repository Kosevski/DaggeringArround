package com.example.daggeringarround.di;

import com.example.daggeringarround.di.auth.AuthModule;
import com.example.daggeringarround.di.auth.AuthViewModelsModule;
import com.example.daggeringarround.di.main.MainFragmentBuildersModule;
import com.example.daggeringarround.di.main.MainModule;
import com.example.daggeringarround.di.main.MainViewModelsModule;
import com.example.daggeringarround.ui.auth.AuthActivity;
import com.example.daggeringarround.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();


}
