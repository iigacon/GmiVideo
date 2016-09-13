package com.gomind.gmivideo.view.Fragment;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieUpComingComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieUpComingModule;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.vmp.presenter.MovieUpComingPresenter;

import javax.inject.Inject;


public class FragmentMovieUpComing extends FragmentMovie {
    @Inject
    MovieUpComingPresenter popularPresenter;

    @Override
    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerMovieUpComingComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .movieUpComingModule(new MovieUpComingModule())
                .build().inject(this);
        presenter=popularPresenter;
    }

    @Override
    public void showDetailMovie(String id) {
        MovieDetailActivity.start(getContext(),id);
    }
}
