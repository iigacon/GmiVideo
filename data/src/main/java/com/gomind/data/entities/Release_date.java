package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 8/28/16.
 */
public class Release_date {
    @SerializedName("certification")
    private String certification;
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @SerializedName("note")
    private String note;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("type")
    private int type;

    public Release_date(String certification, String iso_639_1, String note, String release_date, int type) {
        this.certification = certification;
        this.iso_639_1 = iso_639_1;
        this.note = note;
        this.release_date = release_date;
        this.type = type;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
