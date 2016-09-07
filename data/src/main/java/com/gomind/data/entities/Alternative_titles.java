package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 8/28/16.
 */
public class Alternative_titles {
    @SerializedName("id")
    private String id;
    @SerializedName("titles")
    private Title titles;

    public Alternative_titles(String id, Title titles) {
        this.id = id;
        this.titles = titles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Title getTitles() {
        return titles;
    }

    public void setTitles(Title titles) {
        this.titles = titles;
    }
}
