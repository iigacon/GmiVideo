package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieTopRateModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentMovieTopRate;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieTopRateModule.class})
public interface MovieTopRateComponent extends ActivityComponent {
    void inject(FragmentMovieTopRate fragmentMovieTopRate);
}
