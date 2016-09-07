package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 8/28/16.
 */
public class Account_states {
    @SerializedName("id")
    private String id;
    @SerializedName("favorite")
    private boolean favorite;
    @SerializedName("rated")
    private Rated Rated;
    @SerializedName("watchlist")
    private boolean watchlist;

    public Account_states(String id, boolean favorite, Rated Rated, boolean watchlist) {
        this.id = id;
        this.favorite = favorite;
        this.Rated = Rated;
        this.watchlist = watchlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Rated getRated() {
        return Rated;
    }

    public void setRated(Rated Rated) {
        this.Rated = Rated;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }
}
