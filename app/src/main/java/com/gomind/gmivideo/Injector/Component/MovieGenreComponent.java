package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieGenreModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentMovieCatalog;

import dagger.Component;
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieGenreModule.class})
public interface MovieGenreComponent {
    void inject(FragmentMovieCatalog fragmentMovieCatalog);
}
