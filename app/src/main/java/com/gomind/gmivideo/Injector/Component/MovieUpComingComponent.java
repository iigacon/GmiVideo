package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieUpComingModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentMovieUpComing;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieUpComingModule.class})
public interface MovieUpComingComponent extends ActivityComponent {
    void inject(FragmentMovieUpComing fragmentMovieUpComing);
}
