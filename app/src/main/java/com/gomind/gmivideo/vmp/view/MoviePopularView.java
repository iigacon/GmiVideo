package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Movie;

import java.util.List;

public interface MoviePopularView extends View {

	void bindMoviePopular(List<Movie> movies);

	void showCharacterList();

	void hideCharactersList();

	void showLoadingMoreCharactersIndicator();

	void hideLoadingMoreCharactersIndicator();

	void hideLoadingIndicator ();

	void showLoadingView();

	void hideLoadingView();

	void showLightError();

	void hideErrorView();

	void showEmptyIndicator();

	void hideEmptyIndicator();

	void updateMoviePopularList(int charactersLimit);

	void showConnectionErrorMessage();

	void showServerErrorMessage();

	void showUknownErrorMessage();

	void showDetailMovie(String movieId);
}
