package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MoviePopular;
import com.gomind.domain.GetMoviePopular;
import com.gomind.gmivideo.vmp.view.MoviePopularView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;



public class MoviePopularPresenter implements Presenter{
    private final GetMoviePopular getMoviePopular;
    private Subscription mMoviePopular;
    private MoviePopularView moviePopularView;
    private List<Movie> movies=new ArrayList<>();

    @Inject
    public MoviePopularPresenter(GetMoviePopular getMoviePopular) {
        this.getMoviePopular=getMoviePopular;
    }


    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {
        mMoviePopular.unsubscribe();
    }

    @Override
    public void attachView(View v) {
        moviePopularView = (MoviePopularView) v;
    }

    @Override
    public void onCreate() {
        askForMoviePopulars();
    }

    public void askForMoviePopulars() {
        mMoviePopular =  getMoviePopular.execute().subscribe(this::onMoviePopularReceived, this::showErrorView);
    }
    public void askNewForMoviePopulars() {
        mMoviePopular =  getMoviePopular.execute().subscribe(this::onNewMoviePopularReceived, this::showErrorView);
    }
    public void onMoviePopularReceived(MoviePopular moviePopular){
        movies.addAll(moviePopular.getResults());
        moviePopularView.bindMoviePopular(movies);
    }
    public void onNewMoviePopularReceived(MoviePopular moviePopular){
        movies.addAll(moviePopular.getResults());
        moviePopularView.updateMoviePopularList(20);
    }
    public void showErrorView(Throwable error) {

    }
    public void onElementClick(int position) {
        moviePopularView.showDetailMovie(movies.get(position).getId());
    }
}
