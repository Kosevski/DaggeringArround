package com.example.daggeringarround.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggeringarround.SessionManager;
import com.example.daggeringarround.models.User;
import com.example.daggeringarround.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: is working");

    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: attempting to login.");
        sessionManager.authenticateWithId(queryUserId(userId));

//        authUser.addSource(source, new Observer<AuthResource<User>>() {
//            @Override
//            public void onChanged(AuthResource<User> user) {
//                authUser.setValue(user);
//                authUser.removeSource(source);
//            }
//        });
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1) {
                                    return AuthResource.error("could not auth", (User) null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
