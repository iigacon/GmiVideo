package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Images;
import com.gomind.domain.GetMovieImage;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Duc on 9/5/16.
 */
public class MovieImagePresenter implements Presenter {
    private MovieDetailView movieDetailView;
    private GetMovieImage getMovieImage;
    private Subscription mMovieImage;
    @Inject
    public MovieImagePresenter(GetMovieImage getMovieImage) {
        this.getMovieImage=getMovieImage;
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
        mMovieImage=getMovieImage.execute().subscribe(this::onMovieImageReceived,this::onError);
    }
    public void onMovieImageReceived(Images images){
        movieDetailView.bindMovieImage(images.getBackdrops());
    }
    public void onError(Throwable error){
        error.printStackTrace();
    }

}
