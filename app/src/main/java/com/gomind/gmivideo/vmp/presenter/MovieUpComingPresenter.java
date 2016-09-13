package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MovieBase;
import com.gomind.domain.GetMovieUpComing;
import com.gomind.gmivideo.vmp.view.MovieBaseView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class MovieUpComingPresenter implements Presenter {
    private GetMovieUpComing getMovieUpComing;
    private Subscription mMovieGenre;
    private MovieBaseView movieBaseView;
    private List<Movie> movies=new ArrayList<>();
    private int page=1;
    @Inject
    public MovieUpComingPresenter(GetMovieUpComing getMovieUpComing) {
        this.getMovieUpComing = getMovieUpComing;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mMovieGenre.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        movieBaseView = (MovieBaseView) v;
    }

    @Override
    public void onCreate() {
        mMovieGenre=getMovieUpComing.execute().subscribe(this::OnMovieGenreReceived,this::OnError);
    }
    @Override
    public void loadMore(){
        getMovieUpComing.setPage(++page);
        mMovieGenre=getMovieUpComing.execute().subscribe(this::OnMovieGenreReceivedMore, this::OnErrorMore);
    }
    public void OnMovieGenreReceived(MovieBase movieBase){
        movies.addAll(movieBase.getResults());
        movieBaseView.bindMovieBase(movies);
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

    public void OnMovieGenreReceivedMore(MovieBase movieBase){
        movies.addAll(movieBase.getResults());
        movieBaseView.bindLoadMore(movieBase.getTotal_results());
    }
    public void OnErrorMore(Throwable error){
        page--;
        error.printStackTrace();
    }
}
