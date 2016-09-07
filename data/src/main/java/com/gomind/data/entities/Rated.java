package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 8/28/16.
 */
public class Rated {
    @SerializedName("value")
    private float rated;

    public Rated(float rated) {
        this.rated = rated;
    }

    public float getRated() {
        return rated;
    }

    public void setRated(float rated) {
        this.rated = rated;
    }
}
