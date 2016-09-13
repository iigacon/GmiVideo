package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MoviePopular;
import com.gomind.domain.GetMoviePopular;
import com.gomind.gmivideo.vmp.view.MovieBaseView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;



public class MoviePopularPresenter implements Presenter{
    private final GetMoviePopular getMoviePopular;
    private Subscription mMoviePopular;
    private MovieBaseView moviePopularView;
    private List<Movie> movies=new ArrayList<>();
    private int page=1;

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
        moviePopularView = (MovieBaseView) v;
    }

    @Override
    public void onCreate() {
        mMoviePopular =  getMoviePopular.execute().subscribe(this::onMoviePopularReceived, this::showErrorView);
    }

    @Override
    public void loadMore() {
        getMoviePopular.setPage(++page);
        mMoviePopular =  getMoviePopular.execute().subscribe(this::onNewMoviePopularReceived, this::showErrorView);
    }

    public void onMoviePopularReceived(MoviePopular moviePopular){
        movies.addAll(moviePopular.getResults());
        moviePopularView.bindMovieBase(movies);
    }
    public void onNewMoviePopularReceived(MoviePopular moviePopular){
        movies.addAll(moviePopular.getResults());
        moviePopularView.bindLoadMore(moviePopular.getResults().size());
    }
    public void showErrorView(Throwable error) {
        page--;
        error.printStackTrace();
    }
    public void onElementClick(int position) {
        moviePopularView.showDetailMovie(movies.get(position).getId());
    }
}
