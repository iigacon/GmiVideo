package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 8/27/16.
 */
public class Tv {
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("genre_ids")
    private List<Integer> genre_ids;
    @SerializedName("id")
    private String id;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("original_name")
    private String original_name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("origin_country")
    private List<String> origin_country;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("name")
    private String name;
    @SerializedName("vote_average")
    private float vote_average;
    @SerializedName("vote_count")
    private int vote_count;

    public Tv(String backdrop_path, String first_air_date, List<Integer> genre_ids, String id, String original_language, String original_name, String overview, List<String> origin_country, String poster_path, float popularity, String name, float vote_average, int vote_count) {
        this.backdrop_path = backdrop_path;
        this.first_air_date = first_air_date;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_name = original_name;
        this.overview = overview;
        this.origin_country = origin_country;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.name = name;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
