package com.gomind.gmivideo.Injector.Module;

import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.data.repository.MovieDataRepository;
import com.gomind.data.repository.Movies;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@Module
public class AppModule {
    private final GmiVideoApplication gmiVideoApplication;

    public AppModule(GmiVideoApplication gmiVideoApplication) {
        this.gmiVideoApplication = gmiVideoApplication;
    }

    @Singleton
    @Provides
    GmiVideoApplication provideGmiVideoApplicationContext() {
        return gmiVideoApplication;
    }

    @Provides
    @Singleton
    Movies provideDataRepository(MovieDataRepository movieDataRepository) {
        return movieDataRepository;
    }
    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }



}
