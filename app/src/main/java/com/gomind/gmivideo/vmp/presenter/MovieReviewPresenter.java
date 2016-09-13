package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Reviews;
import com.gomind.domain.GetMovieReview;
import com.gomind.gmivideo.vmp.view.MovieReviewView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;


public class MovieReviewPresenter implements Presenter {
    private GetMovieReview getMovieReview;
    private MovieReviewView movieReviewView;
    private Subscription mMovieReview;
    private int page=1;
    @Inject
    public MovieReviewPresenter(GetMovieReview getMovieReview) {
        this.getMovieReview = getMovieReview;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mMovieReview.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        movieReviewView= (MovieReviewView) v;
    }

    @Override
    public void onCreate() {
        mMovieReview= getMovieReview.execute().subscribe(this::OnMovieReviewReceived,this::OnError);
    }

    @Override
    public void loadMore() {
        mMovieReview= getMovieReview.execute().subscribe(this::OnMovieReviewReceived,this::OnError);
    }

    public void OnMovieReviewReceived(Reviews reviews){
        movieReviewView.bindReview(reviews.getResults());
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }
}
