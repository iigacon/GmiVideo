package com.gomind.gmivideo.view.activity;

import org.parceler.Parcel;

/**
 * Created by Duc on 8/28/16.
 */
@Parcel
public class Image {
    private String file_path;
    private int width;
    private int height;
    private String iso_639_1;
    private float aspect_ratio;
    private float vote_average;
    private int vote_count;

    public Image(String file_path, int width, int height, String iso_639_1, float aspect_ratio, float vote_average, int vote_count) {
        this.file_path = file_path;
        this.width = width;
        this.height = height;
        this.iso_639_1 = iso_639_1;
        this.aspect_ratio = aspect_ratio;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public float getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(float aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
