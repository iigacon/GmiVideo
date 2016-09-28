package com.gomind.domain;

import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;


public class PostMovieWatchList extends UseCase<com.gomind.data.entities.PostWatchList>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private String session_id;
    private String media_id;
    private boolean watchlist;
    private String id;
    @Inject
    public PostMovieWatchList(Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<com.gomind.data.entities.PostWatchList> buildObserable() {
        return movies.postWatchList(session_id, id, media_id, watchlist)
                .observeOn(uiThread)
                .subscribeOn(executorThread);

    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    public void setId(String id) {
        this.id = id;
    }
}
