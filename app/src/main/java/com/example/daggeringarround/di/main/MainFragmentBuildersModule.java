package com.example.daggeringarround.di.main;

import com.example.daggeringarround.ui.main.posts.PostFragment;
import com.example.daggeringarround.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();

}
