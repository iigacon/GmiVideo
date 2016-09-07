package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Credits;
import com.gomind.domain.GetMovieCredit;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Duc on 9/6/16.
 */
public class MovieCreditPresenter implements Presenter{
    private GetMovieCredit getMovieCredit;
    private MovieDetailView movieDetailView;
    private Subscription mMovieCredit;
    @Inject
    public MovieCreditPresenter(GetMovieCredit getMovieCredit) {
        this.getMovieCredit = getMovieCredit;
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
        movieDetailView= (MovieDetailView) v;
    }

    @Override
    public void onCreate() {
        mMovieCredit=getMovieCredit.execute().subscribe(this::onMovieCreditReceived,this::onError);
    }
    public void onMovieCreditReceived(Credits credits){
        movieDetailView.bindCreditMovie(credits);
    }
    public void onError(Throwable error){
        error.printStackTrace();
    }
}
