package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.MovieDetail;
import com.gomind.domain.GetMovieDetail;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Duc on 9/4/16.
 */
public class MovieDetailPresenter implements Presenter {
    private final GetMovieDetail getMovieDetail;
    private MovieDetailView movieDetailView;
    private Subscription mMovieDetail;
    @Inject
    public MovieDetailPresenter(GetMovieDetail getMovieDetail) {
        this.getMovieDetail = getMovieDetail;
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
        mMovieDetail=getMovieDetail.execute().subscribe(this::onMovieDetailReceived, this::onErrorMovieDetail);
    }
    public void onMovieDetailReceived(MovieDetail movieDetail){
        movieDetailView.bindMovieDetail(movieDetail);
    }
    public void onErrorMovieDetail(Throwable error){
        error.printStackTrace();
    }
}
