package com.gomind.domain;

import com.gomind.data.entities.PersonPopulars;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;


public class SearchPerson extends UseCase<PersonPopulars>{
    private String query;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private int page;
    @Inject
    public SearchPerson(Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<PersonPopulars> buildObserable() {
        return movies.searchPerson(query, page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
