package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.PostWatchList;
import com.gomind.domain.PostMovieWatchList;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;


public class PostWatchListPresenter implements Presenter {

    private PostMovieWatchList postMovieWatchList;
    private MovieDetailView movieDetailView;
    private Subscription mWatchListMovie;
    private String session_id;
    private String id;
    private boolean watchlist;
    private String media_id;
    @Inject
    public PostWatchListPresenter(PostMovieWatchList postMovieWatchList) {
        this.postMovieWatchList=postMovieWatchList;
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
        movieDetailView = (MovieDetailView) v;
    }

    @Override
    public void onCreate() {
        postMovieWatchList.setSession_id(session_id);
        postMovieWatchList.setId(id);
        postMovieWatchList.setMedia_id(media_id);
        //TODO
        postMovieWatchList.setWatchlist(watchlist);
        postMovieWatchList.execute().subscribe(this::OnReceived, this::OnError);
    }
    @Override
    public void loadMore() {
      //Nothing
    }
    public void OnReceived(PostWatchList postWatchList) {
        movieDetailView.bindPostWatchList(postWatchList);
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

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
