package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/13/16.
 */
public class PersonMovieCredit {
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("character")
    private String character;
    @SerializedName("credit_id")
    private String credit_id;
    @SerializedName("id")
    private String id;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("title")
    private String title;

    public PersonMovieCredit(boolean adult, String character, String credit_id, String id, String original_title, String poster_path, String release_date, String title) {
        this.adult = adult;
        this.character = character;
        this.credit_id = credit_id;
        this.id = id;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
