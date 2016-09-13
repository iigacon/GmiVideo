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
public class GetMovieGenre extends UseCase<MovieBase> {
    private String idMovie;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private int page=1;
    @Inject
    public GetMovieGenre(String idMovie, Movies movies,
                         @Named("ui_thread") Scheduler uiThread,
                         @Named("executor_thread") Scheduler executorThread) {
        this.idMovie = idMovie;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<MovieBase> buildObserable() {
        return movies.movieGenre(idMovie,page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
    public void setPage(int page){
        this.page=page;
    }
}
