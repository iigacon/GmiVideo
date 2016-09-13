package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Movie;

import java.util.List;

public interface MovieBaseView extends View {
	void bindMovieBase(List<Movie> movies);
	void bindLoadMore(int count);
	void showDetailMovie(String id);
}
