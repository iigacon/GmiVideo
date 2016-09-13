package com.gomind.domain;

import com.gomind.data.entities.MovieLists;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;


public class GetMovieCatelogy extends UseCase<MovieLists>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetMovieCatelogy(Movies movies,
                            @Named("ui_thread") Scheduler uiThread,
                            @Named("executor_thread") Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<MovieLists> buildObserable() {
        return movies.movieCatelogy()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
