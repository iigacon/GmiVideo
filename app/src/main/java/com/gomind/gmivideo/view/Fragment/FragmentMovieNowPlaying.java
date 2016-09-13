package com.gomind.gmivideo.view.Fragment;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieNowPlayingComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieNowPlayingModule;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.vmp.presenter.MovieNowPlayingPresenter;

import javax.inject.Inject;


public class FragmentMovieNowPlaying extends FragmentMovie {
    @Inject
    MovieNowPlayingPresenter popularPresenter;

    @Override
    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerMovieNowPlayingComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .movieNowPlayingModule(new MovieNowPlayingModule())
                .build().inject(this);
        presenter=popularPresenter;
    }

    @Override
    public void showDetailMovie(String id) {
        MovieDetailActivity.start(getContext(),id);
    }
}
