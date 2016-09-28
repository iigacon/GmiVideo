package com.gomind.gmivideo.Injector.Module;


import com.gomind.data.repository.Movies;
import com.gomind.domain.GetWatchListMovie;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;


@Module
public class WatchListMovieModule {
    @Activity @Provides
    public GetWatchListMovie provideGetWatchListMovie(Movies movies,
                                                      @Named("ui_thread")Scheduler uiThread,
                                                      @Named("executor_thread")Scheduler executorThread){
        return new GetWatchListMovie(movies, uiThread, executorThread);
    }
}
