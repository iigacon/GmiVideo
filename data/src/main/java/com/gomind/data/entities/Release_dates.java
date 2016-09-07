package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 8/28/16.
 */
public class Release_dates {
    @SerializedName("iso_3166_1")
    private String iso_3166_1;
    @SerializedName("release_dates")
    private List<Release_date> release_dates;

    public Release_dates(String iso_3166_1, List<Release_date> release_dates) {
        this.iso_3166_1 = iso_3166_1;
        this.release_dates = release_dates;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public List<Release_date> getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(List<Release_date> release_dates) {
        this.release_dates = release_dates;
    }
}
