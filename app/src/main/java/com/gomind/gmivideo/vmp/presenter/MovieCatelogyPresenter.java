package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.MovieLists;
import com.gomind.domain.GetMovieCatelogy;
import com.gomind.gmivideo.vmp.view.MovieCatelogyView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;


public class MovieCatelogyPresenter implements Presenter{

    private GetMovieCatelogy getMovieCatelogy;
    private MovieCatelogyView movieCatelogyView;
    private Subscription mMovieCatelogy;
    @Inject
    public MovieCatelogyPresenter(GetMovieCatelogy getMovieCatelogy) {
        this.getMovieCatelogy = getMovieCatelogy;
    }

    @Override
    public void onStart() {


    }

    @Override
    public void onStop() {
        mMovieCatelogy.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        movieCatelogyView= (MovieCatelogyView) v;
    }

    @Override
    public void onCreate() {
        mMovieCatelogy=getMovieCatelogy.execute().subscribe(this::OnMovieCatelogyReceived,this::OnError);
    }

    @Override
    public void loadMore() {

    }

    public void OnMovieCatelogyReceived(MovieLists movieLists){
        movieCatelogyView.bindMovieCatelogy(movieLists.getGenres());
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }
}
