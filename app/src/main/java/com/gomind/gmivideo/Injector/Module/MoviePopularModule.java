package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMoviePopular;
import com.gomind.gmivideo.Injector.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class MoviePopularModule {
    private final int page;

    public MoviePopularModule(int page) {
        this.page = page;
    }

    @Provides
    @Activity
    GetMoviePopular provideGetMoviePopular(
            Movies movies,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread) {

        return new GetMoviePopular(page, movies, uiThread, executorThread);
    }


}
