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
public class GetMovieUpComing extends UseCase<MovieBase>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private int page=1;
    @Inject
    public GetMovieUpComing(Movies movies,
                            @Named("ui_thread") Scheduler uiThread,
                            @Named("executor_thread") Scheduler executorThread) {
        this.page=page;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<MovieBase> buildObserable() {
        return movies.movieUpComing(page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
    public void setPage(int page){
        this.page=page;
    }
}
