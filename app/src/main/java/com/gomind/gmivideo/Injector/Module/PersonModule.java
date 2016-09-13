package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetPerson;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class PersonModule {
    private String id;

    public PersonModule(String id) {
        this.id = id;
    }

    @Provides @Activity
    public GetPerson provideGetPerson(Movies movies,
                                      @Named("ui_thread")Scheduler uiThread,
                                      @Named("executor_thread")Scheduler executorThread){
        return new GetPerson(id, movies,uiThread,executorThread);
    }
}
