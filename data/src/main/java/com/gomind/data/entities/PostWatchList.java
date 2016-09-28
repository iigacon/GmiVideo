package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/16/16.
 */
public class PostWatchList {
    @SerializedName("status_code")
    private int status_code;
    @SerializedName("status_message")
    private String status_message;

    public PostWatchList(int status_code, String status_message) {
        this.status_code = status_code;
        this.status_message = status_message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
