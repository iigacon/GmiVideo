package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.PostWatchList;
import com.gomind.data.entities.WatchListMovie;

import java.util.List;


public interface WatchListMovieView {
    void bindWatchListMovie(List<WatchListMovie> watchListMovies);
    void bindPostWatchList(PostWatchList postWatchList);
    void bindError(Throwable error);
    void bindLoadMore(int count);
}
