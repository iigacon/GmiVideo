package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieGenre;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Duc on 9/9/16.
 */
@Module
public class MovieGenreModule {

    private String idMovie;

    public MovieGenreModule(String idMovie) {
        this.idMovie = idMovie;
    }

    @Provides
    @Activity
    public GetMovieGenre provideGetMovieGenre(Movies movies,
                                              @Named("ui_thread") Scheduler uiThread,
                                              @Named("executor_thread") Scheduler executorThread) {
        return new GetMovieGenre(idMovie, movies, uiThread, executorThread);
    }
}
