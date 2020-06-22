package com.example.daggeringarround.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggeringarround.SessionManager;
import com.example.daggeringarround.models.User;
import com.example.daggeringarround.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;
    
    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: spremno e");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
    
}
