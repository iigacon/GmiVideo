package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.AccountModule;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieCatelogyModule;
import com.gomind.gmivideo.Injector.Module.PostWatchListModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.activity.MainActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {PostWatchListModule.class,ActivityModule.class, MovieCatelogyModule.class, AccountModule.class})
public interface MovieCatelogyComponent {
    void inject(MainActivity mainActivity);
}
