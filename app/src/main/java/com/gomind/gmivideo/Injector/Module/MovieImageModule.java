package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieImage;
import com.gomind.gmivideo.Injector.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class MovieImageModule {
    private String idMovie;
    public MovieImageModule(String idMovie) {
        this.idMovie = idMovie;
    }

    @Provides @Activity
    public GetMovieImage provideGetMovieImage(Movies movies,
                                              @Named("ui_thread")Scheduler uiThread,
                                              @Named("executor_thread")Scheduler executorThread){
        return new GetMovieImage(idMovie,movies,uiThread,executorThread);
    }
}
