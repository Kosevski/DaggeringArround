package com.example.daggeringarround.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggeringarround.R;
import com.example.daggeringarround.di.ViewModelFactoryModule;
import com.example.daggeringarround.models.Post;
import com.example.daggeringarround.ui.main.Resource;
import com.example.daggeringarround.util.VerticalSpaceItemDecoration;
import com.example.daggeringarround.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource!=null){
                   switch (listResource.status){
                       case LOADING:{
                           Log.d(TAG, "onChanged: Loading..");
                           break;
                       }
                       case SUCCESS:{
                           Log.d(TAG, "onChanged: posts are here");
                           adapter.setPosts(listResource.data);
                           break;
                       }
                       
                       case ERROR:{
                           Log.d(TAG, "onChanged: ERROR" + listResource.message);
                           break;
                       }
                   }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);recyclerView.setAdapter(adapter);
    }
}
