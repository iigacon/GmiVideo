package com.gomind.domain;

import com.gomind.data.entities.Account;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/16/16.
 */
public class GetAccount extends UseCase<Account>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private String session_id;
    @Inject
    public GetAccount(Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Account> buildObserable() {
        return movies.account(session_id)
                .observeOn(uiThread)
                .subscribeOn(executorThread);

    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
