package com.gomind.domain;

import com.gomind.data.entities.MovieDetail;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/4/16.
 */
public class GetMovieDetail extends UseCase<MovieDetail> {
    private final String idMovie;
    private Scheduler uiThead;
    private Scheduler executeThread;
    private Movies movies;
    @Inject
    public GetMovieDetail(String idMovie, Movies movies,
                          @Named("ui_thread")Scheduler uiThread,
                          @Named("executor_thread")Scheduler executeThread) {

        this.idMovie = idMovie;
        this.movies = movies;
        this.uiThead = uiThread;
        this.executeThread = executeThread;
    }

    @Override
    public Observable<MovieDetail> buildObserable() {
        return movies.movieDetail(idMovie)
                .observeOn(uiThead)
                .subscribeOn(executeThread);
    }
}
