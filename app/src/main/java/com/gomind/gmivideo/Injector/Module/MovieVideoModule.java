package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieVideo;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class MovieVideoModule {
    private String idMovie;

    public MovieVideoModule(String idMovie) {
        this.idMovie = idMovie;
    }
    @Provides @Activity
    public GetMovieVideo provideGetMovieVideo(Movies movies,
                                              @Named("ui_thread")Scheduler uiThread,
                                              @Named("executor_thread")Scheduler executorThread){
        return new GetMovieVideo(idMovie, movies,uiThread,executorThread);
    }
}
