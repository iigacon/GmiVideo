package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/13/16.
 */
public class ImagePerson {
    @SerializedName("file_path")
    private String file_path;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("aspect_ratio")
    private float aspect_ratio;

    public ImagePerson(String file_path, int width, int height, float aspect_ratio) {
        this.file_path = file_path;
        this.width = width;
        this.height = height;
        this.aspect_ratio = aspect_ratio;
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


    public float getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(float aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }
}
