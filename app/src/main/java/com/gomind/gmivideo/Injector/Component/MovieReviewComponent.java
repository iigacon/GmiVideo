package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieReViewModule;
import com.gomind.gmivideo.view.activity.ReviewActivity;

import dagger.Component;

/**
 * Created by Duc on 9/8/16.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MovieReViewModule.class})
public interface MovieReviewComponent {
    void inject(ReviewActivity reviewActivity);
}
