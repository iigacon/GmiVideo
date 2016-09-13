package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieUpComing;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Duc on 9/10/16.
 */
@Module
public class MovieUpComingModule {

    @Provides @Activity
    public GetMovieUpComing provideGetMovieUpComing(Movies movies,
                                                    @Named("ui_thread")Scheduler uiThread,
                                                    @Named("executor_thread")Scheduler executorThread){
        return new GetMovieUpComing(movies,uiThread,executorThread);
    }
}
