package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/16/16.
 */
public class WatchListMovie {
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("id")
    private String id;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private float vote_average;
    @SerializedName("vote_count")
    private double vote_count;

    public WatchListMovie(String backdrop_path, String id, String original_title, String release_date, String poster_path, String title, float vote_average, double vote_count) {
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public double getVote_count() {
        return vote_count;
    }

    public void setVote_count(double vote_count) {
        this.vote_count = vote_count;
    }
}
