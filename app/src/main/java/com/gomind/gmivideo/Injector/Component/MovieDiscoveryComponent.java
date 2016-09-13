package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieDiscoveryModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentMovieDiscovery;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieDiscoveryModule.class})
public interface MovieDiscoveryComponent extends ActivityComponent {
    void inject(FragmentMovieDiscovery fragmentMovieDiscovery);
}
