package com.gomind.domain;

import com.gomind.data.entities.Credits;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/6/16.
 */
public class GetMovieCredit extends UseCase<Credits>{
    private String idMovie;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetMovieCredit(String idMovie, Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.idMovie = idMovie;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }


    @Override
    public Observable<Credits> buildObserable() {
        return movies.movieCredits(idMovie)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
