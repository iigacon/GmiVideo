package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieNowPlayingModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentMovieNowPlaying;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieNowPlayingModule.class})
public interface MovieNowPlayingComponent extends ActivityComponent {
    void inject(FragmentMovieNowPlaying fragmentMovieNowPlaying);
}
