package com.example.daggeringarround.di.main;

import androidx.lifecycle.ViewModel;

import com.example.daggeringarround.di.ViewModelKey;
import com.example.daggeringarround.ui.main.posts.PostsViewModel;
import com.example.daggeringarround.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostViewModel(PostsViewModel postsViewModel);

}
