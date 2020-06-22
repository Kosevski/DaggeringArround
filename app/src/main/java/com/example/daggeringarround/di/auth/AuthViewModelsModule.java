package com.example.daggeringarround.di.auth;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.daggeringarround.di.ViewModelKey;
import com.example.daggeringarround.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
