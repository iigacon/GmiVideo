package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc on 9/16/16.
 */
public class Session {
    @SerializedName("session_id")
    private String session_id;
    @SerializedName("success")
    private boolean success;

    public Session(String session_id, boolean success) {
        this.session_id = session_id;
        this.success = success;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
