package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.MovieSimilars;
import com.gomind.domain.GetMovieSimilar;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Duc on 9/5/16.
 */
public class MovieSimilarPresenter implements Presenter {
    private GetMovieSimilar getMovieSimilar;
    private MovieDetailView movieDetailView;
    private Subscription mMovieSimilar;
    @Inject
    public MovieSimilarPresenter(GetMovieSimilar getMovieSimilar) {
        this.getMovieSimilar = getMovieSimilar;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        this.movieDetailView= (MovieDetailView) v;
    }

    @Override
    public void onCreate() {
        mMovieSimilar=getMovieSimilar.execute().subscribe(this::onMovieSimilarReceived,this::onError);
    }
    public void onMovieSimilarReceived(MovieSimilars movieSimilars){
        movieDetailView.bindMovieSimilar(movieSimilars.getResults());
    }
    public void onError(Throwable error){
        error.printStackTrace();
    }
}
