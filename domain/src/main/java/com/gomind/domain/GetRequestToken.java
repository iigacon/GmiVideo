package com.gomind.domain;

import com.gomind.data.entities.RequestToken;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/16/16.
 */
public class GetRequestToken extends UseCase<RequestToken>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetRequestToken(Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<RequestToken> buildObserable() {
        return movies.requestToken()
                .observeOn(uiThread)
                .subscribeOn(executorThread);

    }
}
