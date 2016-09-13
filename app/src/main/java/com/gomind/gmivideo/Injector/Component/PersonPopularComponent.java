package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.PersonPopularModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.Fragment.FragmentPersonPopular;

import dagger.Component;

/**
 * Created by Duc on 9/13/16.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, PersonPopularModule.class})
public interface PersonPopularComponent {
    void inject(FragmentPersonPopular fragmentPersonPopular);
}
