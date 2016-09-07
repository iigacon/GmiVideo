package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieSimilar;
import com.gomind.gmivideo.Injector.Activity;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class MovieSimilarModule {
    private final int page;
    private final String idMovie;
    public MovieSimilarModule(int page, String idMovie) {
        this.page = page;
        this.idMovie=idMovie;
    }

    @Provides @Activity
    public GetMovieSimilar provideGetMovieSimilar(Movies movies,
                                                  @Named("ui_thread")Scheduler uiThread,
                                                  @Named("executor_thread")Scheduler executorThread
                                                  ){
        return new GetMovieSimilar(idMovie, page, movies, uiThread, executorThread);
    }
}
