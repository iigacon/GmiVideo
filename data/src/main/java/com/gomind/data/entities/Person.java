package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 9/13/16.
 */
public class Person {
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("also_known_as")
    private List<String> also_known_as;
    @SerializedName("biography")
    private String biography;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("deathday")
    private String deathday;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("place_of_birth")
    private String place_of_birth;
    @SerializedName("profile_path")
    private String profile_path;

    public Person(boolean adult, List<String> also_known_as, String biography, String birthday, String deathday, String homepage, String id, String name, String place_of_birth, String profile_path) {
        this.adult = adult;
        this.also_known_as = also_known_as;
        this.biography = biography;
        this.birthday = birthday;
        this.deathday = deathday;
        this.homepage = homepage;
        this.id = id;
        this.name = name;
        this.place_of_birth = place_of_birth;
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(List<String> also_known_as) {
        this.also_known_as = also_known_as;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
