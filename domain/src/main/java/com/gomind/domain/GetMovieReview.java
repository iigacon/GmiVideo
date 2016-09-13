package com.gomind.domain;

import com.gomind.data.entities.Reviews;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/8/16.
 */
public class GetMovieReview extends UseCase<Reviews>{
    private String idMovie;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private int page=1;
    @Inject
    public GetMovieReview(String idMovie, Movies movies,
                          @Named("ui_thread") Scheduler uiThread,
                          @Named("executor_thread") Scheduler executorThread) {
        this.idMovie = idMovie;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Reviews> buildObserable() {
        return movies.movieReviews(idMovie,page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
    public void setPage(int page){
        this.page=page;
    }
}
