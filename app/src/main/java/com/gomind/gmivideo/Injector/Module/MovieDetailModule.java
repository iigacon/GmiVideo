package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieDetail;
import com.gomind.gmivideo.Injector.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class MovieDetailModule {
    private final String idMovie;

    public MovieDetailModule(String idMovie) {
        this.idMovie = idMovie;
    }

    @Provides
    @Activity
    public GetMovieDetail provideGetMovieDetail(Movies movies,
                                                @Named("ui_thread") Scheduler uiThread,
                                                @Named("executor_thread") Scheduler executeThread) {
        return new GetMovieDetail(idMovie, movies, uiThread, executeThread);
    }
}
