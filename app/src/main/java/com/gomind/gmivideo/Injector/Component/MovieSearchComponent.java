package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieSearchModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.activity.SearchMovieActivity;

import dagger.Component;

/**
 * Created by Duc on 9/11/16.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieSearchModule.class})
public interface MovieSearchComponent {
    void inject(SearchMovieActivity searchMovieActivity);
}
