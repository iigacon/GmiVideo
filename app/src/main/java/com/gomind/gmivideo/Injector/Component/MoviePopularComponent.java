package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Activity;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MoviePopularModule;
import com.gomind.gmivideo.view.activity.MainActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MoviePopularModule.class})
public interface MoviePopularComponent extends ActivityComponent {
    void inject (MainActivity activity);
}
