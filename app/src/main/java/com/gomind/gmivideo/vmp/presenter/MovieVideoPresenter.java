package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Videos;
import com.gomind.domain.GetMovieVideo;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Duc on 9/5/16.
 */
public class MovieVideoPresenter implements Presenter {
    private MovieDetailView movieDetailView;
    private GetMovieVideo getMovieVideo;
    private Subscription subscription;
    @Inject
    public MovieVideoPresenter(GetMovieVideo getMovieVideo) {
        this.getMovieVideo = getMovieVideo;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {
        subscription.unsubscribe();
    }

    @Override
    public void attachView(View v) {
        movieDetailView= (MovieDetailView) v;
    }

    @Override
    public void onCreate() {
        subscription=getMovieVideo.execute().subscribe(this::onMovieVideoReceived,this::onError);
    }
    public void onMovieVideoReceived(Videos videos){
        movieDetailView.playYoutubeTrailer(videos.getResults());
    }
    public void onError(Throwable error){
        error.printStackTrace();
    }
}
