package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Movie;

import java.util.List;

/**
 * Created by Duc on 9/9/16.
 */
public interface MovieGenreView extends View {
    void bindMovieBase(List<Movie> movies);
    void bindLoadMore(int count);
}
