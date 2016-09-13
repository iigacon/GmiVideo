package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MovieBase;
import com.gomind.domain.GetMovieGenre;
import com.gomind.gmivideo.vmp.view.MovieGenreView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class MovieGenrePresenter implements Presenter {
    private GetMovieGenre getMovieGenre;
    private Subscription mMovieGenre;
    private MovieGenreView movieGenreView;
    private List<Movie> movies=new ArrayList<>();
    private int page=1;
    @Inject
    public MovieGenrePresenter(GetMovieGenre getMovieGenre) {
        this.getMovieGenre = getMovieGenre;
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
       movieGenreView = (MovieGenreView) v;
    }

    @Override
    public void onCreate() {
        mMovieGenre=getMovieGenre.execute().subscribe(this::OnMovieGenreReceived,this::OnError);
    }
    @Override
    public void loadMore(){
        getMovieGenre.setPage(++page);
        mMovieGenre=getMovieGenre.execute().subscribe(this::OnMovieGenreReceivedMore, this::OnErrorMore);
    }
    public void OnMovieGenreReceived(MovieBase movieBase){
        movies.addAll(movieBase.getResults());
        movieGenreView.bindMovieBase(movies);
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

    public void OnMovieGenreReceivedMore(MovieBase movieBase){
        movies.addAll(movieBase.getResults());
        movieGenreView.bindLoadMore(movieBase.getTotal_results());
    }
    public void OnErrorMore(Throwable error){
        page--;
        error.printStackTrace();
    }
}
