package com.gomind.domain;

import com.gomind.data.entities.PersonMovieCredits;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/13/16.
 */
public class GetMovieCastPerson extends UseCase<PersonMovieCredits>{
    private String id;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetMovieCastPerson(String id, Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.id = id;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<PersonMovieCredits> buildObserable() {
        return movies.getMovieCastPerson(id)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
