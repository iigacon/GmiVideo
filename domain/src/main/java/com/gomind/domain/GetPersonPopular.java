package com.gomind.domain;

import com.gomind.data.entities.PersonPopulars;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/13/16.
 */
public class GetPersonPopular extends UseCase<PersonPopulars>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private double page;
    @Inject
    public GetPersonPopular(Movies movies,
                            @Named("ui_thread") Scheduler uiThread,
                            @Named("executor_thread") Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    public double getPage() {
        return page;
    }

    public void setPage(double page) {
        this.page = page;
    }

    @Override
    public Observable<PersonPopulars> buildObserable() {
        return movies.personPopular(page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
