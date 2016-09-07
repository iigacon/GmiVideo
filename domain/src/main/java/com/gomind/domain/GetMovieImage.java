package com.gomind.domain;

import com.gomind.data.entities.Images;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/5/16.
 */
public class GetMovieImage extends UseCase<Images>{
    private String idMovie;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetMovieImage(String idMovie, Movies movies,
                         @Named("ui_thread") Scheduler uiThread,
                         @Named("executor_thread") Scheduler executorThread) {
        this.idMovie = idMovie;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Images> buildObserable() {
        return movies.movieImages(idMovie)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
