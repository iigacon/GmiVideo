package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.ImagePersonModule;
import com.gomind.gmivideo.Injector.Module.MovieCastPersonModule;
import com.gomind.gmivideo.Injector.Module.PersonModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.activity.PersonDetailActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, PersonModule.class, ImagePersonModule.class, MovieCastPersonModule.class})
public interface PersonDetailComponent {
    void inject(PersonDetailActivity personDetailActivity);
}
