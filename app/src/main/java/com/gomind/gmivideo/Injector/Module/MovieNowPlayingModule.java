package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieNowPlaying;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Duc on 9/10/16.
 */
@Module
public class MovieNowPlayingModule {
    @Provides @Activity
    public GetMovieNowPlaying provideGetMovieUpComing(Movies movies,
                                                    @Named("ui_thread")Scheduler uiThread,
                                                    @Named("executor_thread")Scheduler executorThread){
        return new GetMovieNowPlaying(movies,uiThread,executorThread);
    }
}
