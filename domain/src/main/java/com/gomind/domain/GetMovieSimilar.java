package com.gomind.domain;

import com.gomind.data.entities.MovieSimilars;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/5/16.
 */
public class GetMovieSimilar extends UseCase<MovieSimilars>{
    private String idMovie;
    private Scheduler uiThread;
    private Scheduler executetorThread;
    private Movies movies;
    private int page;
    @Inject
    public GetMovieSimilar(String idMovie,int page, Movies movies,
                           @Named("ui_thread") Scheduler uiThread,
                           @Named("executor_thread") Scheduler executetorThread) {
        this.idMovie = idMovie;
        this.uiThread = uiThread;
        this.executetorThread = executetorThread;
        this.movies = movies;
        this.page=page;
    }

    @Override
    public Observable<MovieSimilars> buildObserable() {
        increasePage();
        return movies.movieSimilars(idMovie, page)
                .observeOn(uiThread)
                .subscribeOn(executetorThread);
    }
    private void increasePage(){
        page+=1;
    }
}
