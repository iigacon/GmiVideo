package com.gomind.gmivideo.view.Fragment;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieTopRateComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieTopRateModule;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.vmp.presenter.MovieTopRatePresenter;

import javax.inject.Inject;


public class FragmentMovieTopRate extends FragmentMovie {
    @Inject
    MovieTopRatePresenter popularPresenter;

    @Override
    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerMovieTopRateComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .movieTopRateModule(new MovieTopRateModule())
                .build().inject(this);
        presenter=popularPresenter;
    }

    @Override
    public void showDetailMovie(String id) {
        MovieDetailActivity.start(getContext(),id);
    }
}
