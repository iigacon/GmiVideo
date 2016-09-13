package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieReview;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;


@Module
public class MovieReViewModule {
    private String idMovie;
    public MovieReViewModule(String idMovie) {
        this.idMovie = idMovie;
    }

    @Provides @Activity
    public GetMovieReview provideMovieReview(Movies movies,
                                             @Named("ui_thread")Scheduler uiThread,
                                             @Named("executor_thread")Scheduler executorThread){
        return new GetMovieReview(idMovie,movies,uiThread,executorThread);
    }
}
