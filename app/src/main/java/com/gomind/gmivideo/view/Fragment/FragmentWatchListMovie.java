package com.gomind.gmivideo.view.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerWatchListMovieComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.WatchListMovieModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.vmp.presenter.WatchListMoviePresenter;

import javax.inject.Inject;


public class FragmentWatchListMovie extends FragmentMovie implements SwipeRefreshLayout.OnRefreshListener{
    public static FragmentWatchListMovie newInstance(String id, String session_id){
        FragmentWatchListMovie fragment=new FragmentWatchListMovie();
        Bundle arg=new Bundle();
        arg.putString("id", id);
        arg.putString("session_id", session_id);
        fragment.setArguments(arg);
        return fragment;
    }
    private SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    WatchListMoviePresenter watchListMoviePresenter;
    private String session_id;
    private String id;
    @Override
    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerWatchListMovieComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .watchListMovieModule(new WatchListMovieModule())
                .build().inject(this);
        session_id=getArguments().getString("session_id");
        id=getArguments().getString("id");
        presenter=watchListMoviePresenter;
        watchListMoviePresenter.setSession_id(session_id);
        watchListMoviePresenter.setId(id);
        swipeRefreshLayout = (SwipeRefreshLayout) viewer.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showDetailMovie(String id) {
        MovieDetailActivity.start(getContext(),id);
    }

    @Override
    public void onRefresh() {
        watchListMoviePresenter.onCreate();
        swipeRefreshLayout.setRefreshing(false);
    }


}
