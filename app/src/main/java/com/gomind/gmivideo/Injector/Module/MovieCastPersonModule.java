package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieCastPerson;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Duc on 9/13/16.
 */
@Module
public class MovieCastPersonModule {
    private String id;

    public MovieCastPersonModule(String id) {
        this.id = id;
    }
    @Activity @Provides
    public GetMovieCastPerson provideGetMovieCastPerson(Movies movies,
                                                         @Named("ui_thread")Scheduler uiThread,
                                                         @Named("executor_thread")Scheduler executorThread){
        return new GetMovieCastPerson(id, movies,uiThread,executorThread);
    }
}
