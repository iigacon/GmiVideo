package com.gomind.gmivideo.Injector.Component;

import android.content.Context;

import com.gomind.gmivideo.Injector.Activity;
import com.gomind.gmivideo.Injector.Module.ActivityModule;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();
}
