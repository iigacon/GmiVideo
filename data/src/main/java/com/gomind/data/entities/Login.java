package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/16/16.
 */
public class Login {
    @SerializedName("request_token")
    private String request_token;
    @SerializedName("success")
    private boolean success;

    public Login(String request_token, boolean success) {
        this.request_token = request_token;
        this.success = success;
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
