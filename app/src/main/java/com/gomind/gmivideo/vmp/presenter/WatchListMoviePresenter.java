package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.WatchListMovies;
import com.gomind.domain.GetWatchListMovie;
import com.gomind.gmivideo.mapper.MapperData;
import com.gomind.gmivideo.vmp.view.MovieBaseView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;


public class WatchListMoviePresenter implements Presenter {

    private GetWatchListMovie getWatchListMovie;
    private MovieBaseView movieBaseView;
    private Subscription mWatchListMovie;
    private String session_id;
    private String id;
    private int page;
    private List<Movie> watchListMovies=new ArrayList<>();
    @Inject
    public WatchListMoviePresenter(GetWatchListMovie getMovieCatelogy) {
        this.getWatchListMovie=getMovieCatelogy;
    }

    @Override
    public void onStart() {


    }

    @Override
    public void onStop() {
        mWatchListMovie.unsubscribe();
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
        page=1;
        watchListMovies.clear();
        getWatchListMovie.setSession_id(session_id);
        getWatchListMovie.setId(id);
        getWatchListMovie.setPage(page);
        getWatchListMovie.execute().subscribe(this::OnReceived, this::OnError);
    }
    @Override
    public void loadMore() {
        getWatchListMovie.setPage(++page);
        getWatchListMovie.execute().subscribe(this::OnReceivedMore, this::OnError);
    }
    public void OnReceived(WatchListMovies watchListMovies) {
        this.watchListMovies.addAll(MapperData.MapperWatchListToMovie(watchListMovies.getResults()));
        movieBaseView.bindMovieBase(this.watchListMovies);
    }
    public void OnReceivedMore(WatchListMovies watchListMovies) {
        this.watchListMovies.addAll(MapperData.MapperWatchListToMovie(watchListMovies.getResults()));
        movieBaseView.bindLoadMore(watchListMovies.getResults().size());
    }
    public void OnError(Throwable error) {
        error.printStackTrace();
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
