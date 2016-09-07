package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 8/28/16.
 */
public class MovieList {
    @SerializedName("description")
    private String description;
    @SerializedName("favorite_count")
    private int favorite_count;
    @SerializedName("id")
    private String id;
    @SerializedName("item_count")
    private int item_count;
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String poster_path;

    public MovieList(String description, int favorite_count, String id, int item_count, String iso_639_1, String name, String poster_path) {
        this.description = description;
        this.favorite_count = favorite_count;
        this.id = id;
        this.item_count = item_count;
        this.iso_639_1 = iso_639_1;
        this.name = name;
        this.poster_path = poster_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
