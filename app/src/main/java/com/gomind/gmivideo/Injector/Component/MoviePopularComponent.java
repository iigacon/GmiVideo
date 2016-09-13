package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MoviePopularModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentMoviePopular;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MoviePopularModule.class})
public interface MoviePopularComponent extends ActivityComponent {
    void inject (FragmentMoviePopular fragmentMoviePopular);
}
