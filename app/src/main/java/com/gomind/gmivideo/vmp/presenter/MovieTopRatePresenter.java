package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MovieBase;
import com.gomind.domain.GetMovieTopRate;
import com.gomind.gmivideo.vmp.view.MovieBaseView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class MovieTopRatePresenter implements Presenter {
    private GetMovieTopRate getMovieTopRate;
    private Subscription mMovieGenre;
    private MovieBaseView movieBaseView;
    private List<Movie> movies=new ArrayList<>();
    private int page=1;
    @Inject
    public MovieTopRatePresenter(GetMovieTopRate getMovieTopRate) {
        this.getMovieTopRate = getMovieTopRate;
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
        mMovieGenre=getMovieTopRate.execute().subscribe(this::OnMovieGenreReceived,this::OnError);
    }
    @Override
    public void loadMore(){
        getMovieTopRate.setPage(++page);
        mMovieGenre=getMovieTopRate.execute().subscribe(this::OnMovieGenreReceivedMore, this::OnErrorMore);
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
