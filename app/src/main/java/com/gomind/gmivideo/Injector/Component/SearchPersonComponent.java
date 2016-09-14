package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.SearchPersonModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.activity.SearchPersonActivity;

import dagger.Component;


@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, SearchPersonModule.class})
public interface SearchPersonComponent {
    void inject(SearchPersonActivity searchPersonActivity);
}
