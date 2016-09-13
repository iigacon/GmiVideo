package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.Movies;
import com.gomind.domain.GetMovieSimilar;
import com.gomind.gmivideo.Injector.Scope.Activity;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class MovieSimilarModule {

    private final String idMovie;
    public MovieSimilarModule(String idMovie) {
        this.idMovie=idMovie;
    }

    @Provides @Activity
    public GetMovieSimilar provideGetMovieSimilar(Movies movies,
                                                  @Named("ui_thread")Scheduler uiThread,
                                                  @Named("executor_thread")Scheduler executorThread
                                                  ){
        return new GetMovieSimilar(idMovie, movies, uiThread, executorThread);
    }
}
