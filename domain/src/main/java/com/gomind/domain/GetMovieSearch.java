package com.gomind.domain;

import com.gomind.data.entities.MovieBase;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;


public class GetMovieSearch extends UseCase<MovieBase> {
    private String query="";
    private int page=1;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;

    @Inject
    public GetMovieSearch(Movies movies,
                          @Named("ui_thread") Scheduler uiThread,
                          @Named("executor_thread") Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<MovieBase> buildObserable() {
        return movies.movieSearch(query, page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    public void setPage(int page) {
        this.page = page;
    }
    public void setQuery(String query){
        this.query=query;
    }
}
