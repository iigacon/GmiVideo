package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Movie;
import com.gomind.domain.GetMovieSearch;
import com.gomind.gmivideo.vmp.view.MovieBaseView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class SearchMoviePresenter implements Presenter {
    private GetMovieSearch getMovieSearch;
    private Subscription mMovieGenre;
    private MovieBaseView movieBaseView;
    private List<Movie> movies=new ArrayList<>();
    private int page;
    private String query;
    @Inject
    public SearchMoviePresenter(GetMovieSearch getMovieSearch) {
        this.getMovieSearch = getMovieSearch;
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

    public void onQuery(String query){
        this.query=query;
    }

    @Override
    public void onCreate() {
        page=1;
        movies.clear();
        if(!"".equals(query)) {
            getMovieSearch.setQuery(query);
            getMovieSearch.setPage(page);
            mMovieGenre = getMovieSearch.execute().subscribe(this::OnMovieGenreReceived, this::OnError);
        }
    }
    @Override
    public void loadMore(){
        getMovieSearch.setPage(++page);
        mMovieGenre=getMovieSearch.execute().subscribe(this::OnMovieGenreReceivedMore, this::OnError);
    }
    public void OnMovieGenreReceived(List<Movie> movies){
        movies.addAll(movies);
        movieBaseView.bindMovieBase(movies);
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

    public void OnMovieGenreReceivedMore(List<Movie> movies){
        movies.addAll(movies);
        movieBaseView.bindLoadMore(movies.size());
    }

}
