package com.gomind.gmivideo.Injector.Component;

import com.gomind.data.repository.Movies;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Module.AppModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;


@Singleton @Component(modules = {AppModule.class})
public interface AppComponent {
    GmiVideoApplication app();
    Movies moviePopulars();
    @Named("ui_thread") Scheduler uiThread();
    @Named("executor_thread") Scheduler executorThread();
}
