package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/16/16.
 */
public class RequestToken {
    @SerializedName("expires_at")
    private String expires_at;
    @SerializedName("request_token")
    private String request_token;
    @SerializedName("success")
    private boolean success;

    public RequestToken(String expires_at, String request_token, boolean success) {
        this.expires_at = expires_at;
        this.request_token = request_token;
        this.success = success;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
