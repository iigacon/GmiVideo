package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 8/28/16.
 */
public class Images {
    @SerializedName("id")
    private String id;
    @SerializedName("backdrops")
    private List<Image> backdrops;

    public Images(String id, List<Image> backdrops) {
        this.id = id;
        this.backdrops = backdrops;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }
}
