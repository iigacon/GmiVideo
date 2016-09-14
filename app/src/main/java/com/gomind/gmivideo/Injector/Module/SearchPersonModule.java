package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.SearchPerson;
import com.gomind.gmivideo.Injector.Scope.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;


@Module
public class SearchPersonModule {
    @Provides
    @Activity
    public SearchPerson provideSearchPerson(Movies movies,
                                            @Named("ui_thread") Scheduler uiThread,
                                            @Named("executor_thread") Scheduler executorThread) {
        return new SearchPerson(movies, uiThread, executorThread);
    }
}
