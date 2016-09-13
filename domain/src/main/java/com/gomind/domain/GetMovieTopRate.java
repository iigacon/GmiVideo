package com.gomind.domain;

import com.gomind.data.entities.MovieBase;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/8/16.
 */
public class GetMovieTopRate extends UseCase<MovieBase> {
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private int page=1;
    @Inject
    public GetMovieTopRate(Movies movies,
                           @Named("ui_thread") Scheduler uiThread,
                           @Named("executor_thread") Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<MovieBase> buildObserable() {
        return movies.movieTopRate(page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
    public void setPage(int page){
        this.page=page;
    }

}
