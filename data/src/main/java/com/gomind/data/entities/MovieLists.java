package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 8/28/16.
 */
public class MovieLists {
   @SerializedName("genres")
    private List<MovieList> genres;

    public MovieLists(List<MovieList> genres) {
        this.genres = genres;
    }

    public List<MovieList> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieList> genres) {
        this.genres = genres;
    }
}
