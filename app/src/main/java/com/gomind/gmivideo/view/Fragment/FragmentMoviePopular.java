package com.gomind.gmivideo.view.Fragment;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMoviePopularComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MoviePopularModule;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.vmp.presenter.MoviePopularPresenter;

import javax.inject.Inject;


public class FragmentMoviePopular extends FragmentMovie {

    @Inject
    MoviePopularPresenter popularPresenter;

    @Override
    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerMoviePopularComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .moviePopularModule(new MoviePopularModule())
                .build().inject(this);
        presenter=popularPresenter;
    }

    @Override
    public void showDetailMovie(String id) {
        MovieDetailActivity.start(getContext(),id);
    }
}
