package com.gomind.domain;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MovieBase;
import com.gomind.data.repository.Movies;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;


public class GetMovieSearch extends UseCase<List<Movie>> {
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
    public Observable<List<Movie>> buildObserable() {
        return movies.movieSearch(query, page)
                .map(new Func1<MovieBase, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieBase movieBase) {
                        return movieBase.getResults();
                    }
                })
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
