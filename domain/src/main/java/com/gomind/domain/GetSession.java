package com.gomind.domain;

import com.gomind.data.entities.Session;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/16/16.
 */
public class GetSession extends UseCase<Session>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private String request_token;
    @Inject
    public GetSession(Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Session> buildObserable() {
        return movies.getSession(request_token)
                .observeOn(uiThread)
                .subscribeOn(executorThread);

    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
