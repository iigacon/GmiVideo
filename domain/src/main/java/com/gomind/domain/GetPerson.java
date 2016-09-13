package com.gomind.domain;

import com.gomind.data.entities.Person;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/13/16.
 */
public class GetPerson extends UseCase<Person>{
    private String id;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetPerson(String id, Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.id = id;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Person> buildObserable() {
        return movies.getPerson(id)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
