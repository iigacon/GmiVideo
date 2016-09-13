package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetPersonPopular;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Duc on 9/13/16.
 */
@Module
public class PersonPopularModule {
    @Provides @Activity
    public GetPersonPopular provideGetPersonPopular(Movies movies,
                                                    @Named("ui_thread")Scheduler uiThread,
                                                    @Named("executor_thread")Scheduler executorThread){
        return  new GetPersonPopular(movies,uiThread,executorThread);
    }
}
