package com.gomind.domain;

import com.gomind.data.entities.ImagePersons;
import com.gomind.data.repository.Movies;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/5/16.
 */
public class GetImagePerson extends UseCase<ImagePersons>{
    private String id;
    private Movies movies;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetImagePerson(String id, Movies movies,
                          @Named("ui_thread") Scheduler uiThread,
                          @Named("executor_thread") Scheduler executorThread) {
        this.id = id;
        this.movies = movies;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<ImagePersons> buildObserable() {
        return movies.getImagePerson(id)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
