package com.gomind.domain;

import com.gomind.data.entities.Login;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/16/16.
 */
public class GetLogin extends UseCase<Login>{
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private String request_token;
    private String username;
    private String password;
    @Inject
    public GetLogin(Movies movies, Scheduler uiThread, Scheduler executorThread) {
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Login> buildObserable() {
        return movies.login(request_token, username, password)
                .observeOn(uiThread)
                .subscribeOn(executorThread);

    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
