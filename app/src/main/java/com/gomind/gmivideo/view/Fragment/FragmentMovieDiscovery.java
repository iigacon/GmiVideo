package com.gomind.gmivideo.view.Fragment;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieDiscoveryComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieDiscoveryModule;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.vmp.presenter.MovieDiscoveryPresenter;

import javax.inject.Inject;


public class FragmentMovieDiscovery extends FragmentMovie {
    @Inject
    MovieDiscoveryPresenter popularPresenter;

    @Override
    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerMovieDiscoveryComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .movieDiscoveryModule(new MovieDiscoveryModule())
                .build().inject(this);
        presenter=popularPresenter;
    }

    @Override
    public void showDetailMovie(String id) {
        MovieDetailActivity.start(getContext(),id);
    }
}
