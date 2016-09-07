package com.gomind.gmivideo.Injector.Component;

import com.gomind.data.repository.Movies;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Module.AppModule;
import com.gomind.gmivideo.Injector.Module.ConfigurationModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;


@Singleton @Component(modules = {AppModule.class, ConfigurationModule.class})
public interface AppComponent {
    GmiVideoApplication app();
    Movies moviePopulars();
    @Named("ui_thread") Scheduler uiThread();
    @Named("executor_thread") Scheduler executorThread();
}
