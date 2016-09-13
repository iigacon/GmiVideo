package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 9/13/16.
 */
public class PersonMovieCredits {
    @SerializedName("id")
    private String id;
    @SerializedName("cast")
    private List<PersonMovieCredit> cast;

    public PersonMovieCredits(String id, List<PersonMovieCredit> cast) {
        this.id = id;
        this.cast = cast;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PersonMovieCredit> getCast() {
        return cast;
    }

    public void setCast(List<PersonMovieCredit> cast) {
        this.cast = cast;
    }
}
