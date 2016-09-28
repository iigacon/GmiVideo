package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.PostWatchListModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieCreditModule;
import com.gomind.gmivideo.Injector.Module.MovieDetailModule;
import com.gomind.gmivideo.Injector.Module.MovieImageModule;
import com.gomind.gmivideo.Injector.Module.MovieSimilarModule;
import com.gomind.gmivideo.Injector.Module.MovieVideoModule;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieDetailModule.class, MovieSimilarModule.class, MovieImageModule.class, MovieVideoModule.class, MovieCreditModule.class, PostWatchListModule.class})
public interface MovieDetailComponent {
    void inject(MovieDetailActivity movieDetailActivity);
}
