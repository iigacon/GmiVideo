package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 9/13/16.
 */
public class PersonPopular {
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("id")
    private String id;
    @SerializedName("known_for")
    private List<PeopleMedia> known_for;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("profile_path")
    private String profile_path;

    public PersonPopular(boolean adult, String id, List<PeopleMedia> known_for, String name, float popularity, String profile_path) {
        this.adult = adult;
        this.id = id;
        this.known_for = known_for;
        this.name = name;
        this.popularity = popularity;
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PeopleMedia> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(List<PeopleMedia> known_for) {
        this.known_for = known_for;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
