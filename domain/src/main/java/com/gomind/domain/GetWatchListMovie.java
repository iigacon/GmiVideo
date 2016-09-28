package com.gomind.domain;

import com.gomind.data.entities.WatchListMovies;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;


public class GetWatchListMovie extends UseCase<WatchListMovies> {
    private String session_id;
    private String id;
    private int page=1;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;

    @Inject
    public GetWatchListMovie(Movies movies,
                             @Named("ui_thread") Scheduler uiThread,
                             @Named("executor_thread") Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<WatchListMovies> buildObserable() {
        return movies.watchListMovie(session_id,id, page)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }


    public void setPage(int page) {
        this.page = page;
    }
    public void setSession_id(String session_id){
        this.session_id=session_id;
    }
    public void setId(String id){
        this.id=id;
    }
}
