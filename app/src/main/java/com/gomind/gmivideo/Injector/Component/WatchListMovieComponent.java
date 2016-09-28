package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.WatchListMovieModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentWatchListMovie;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, WatchListMovieModule.class})
public interface WatchListMovieComponent {
    void inject(FragmentWatchListMovie fragmentWatchListMovie);
}
