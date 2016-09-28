package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetLogin;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Duc on 9/16/16.
 */
@Module
public class LoginModule {
    @Activity @Provides
    public GetLogin provideGetLogin(Movies movies,
                                    @Named("ui_thread")Scheduler uiThread,
                                    @Named("executor_thread")Scheduler executorThread){
        return  new GetLogin(movies, uiThread, executorThread);
    }
}
