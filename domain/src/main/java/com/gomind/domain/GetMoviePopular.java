package com.gomind.domain;


import com.gomind.data.entities.MoviePopular;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;


public class GetMoviePopular extends UseCase<MoviePopular> {
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private int page=1;
    private final Movies movies;

    @Inject
    public GetMoviePopular(Movies movies,
                           @Named("ui_thread") Scheduler uiThread,
                           @Named("executor_thread") Scheduler executorThread) {
        this.mUiThread = uiThread;
        this.mExecutorThread = executorThread;
        this.movies=movies;
    }

    @Override
    public Observable<MoviePopular> buildObserable() {
        return movies.moviePopulars(page)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
    public void setPage(int page){
        this.page=page;
    }
}
