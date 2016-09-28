package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Credits;
import com.gomind.data.entities.Image;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MovieSimilar;
import com.gomind.data.entities.PostWatchList;
import com.gomind.data.entities.Video;

import java.util.List;

public interface MovieDetailView extends View{
    void bindMovieDetail(MovieDetail movieDetail);
    void bindMovieSimilar(List<MovieSimilar> movieSimilars);
    void bindMovieImage(List<Image> images);
    void playYoutubeTrailer(List<Video> videoTrailer);
    void bindCreditMovie(Credits credits);
    void videoMovie(String idMovie);
    void imageMovie(String idMovie);
    void bindPostWatchList(PostWatchList postWatchList);
}
