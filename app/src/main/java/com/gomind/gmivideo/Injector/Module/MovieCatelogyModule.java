package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieCatelogy;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;


@Module
public class MovieCatelogyModule {
    @Provides @Activity
    public GetMovieCatelogy provideGetMovieCatelogy(Movies movies,
                                                    @Named("ui_thread")Scheduler uiThread,
                                                    @Named("executor_thread")Scheduler executorThread){
        return new GetMovieCatelogy(movies,uiThread,executorThread);
    }
}
