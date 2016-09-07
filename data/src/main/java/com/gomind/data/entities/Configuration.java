package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 8/27/16.
 */
public class Configuration {
    @SerializedName("base_url")
    private String base_url;
    @SerializedName("secure_base_url")
    private String secure_base_url;
    @SerializedName("backdrop_sizes")
    private List<String> backdrop_sizes;
    @SerializedName("logo_sizes")
    private List<String> logo_sizes;
    @SerializedName("poster_sizes")
    private List<String> poster_sizes;
    @SerializedName("profile_sizes")
    private List<String> profile_sizes;
    @SerializedName("still_sizes")
    private List<String> still_sizes;
    @SerializedName("change_keys")
    private List<String> change_keys;

    public Configuration(String base_url, String secure_base_url, List<String> backdrop_sizes, List<String> logo_sizes, List<String> poster_sizes, List<String> profile_sizes, List<String> still_sizes, List<String> change_keys) {
        this.base_url = base_url;
        this.secure_base_url = secure_base_url;
        this.backdrop_sizes = backdrop_sizes;
        this.logo_sizes = logo_sizes;
        this.poster_sizes = poster_sizes;
        this.profile_sizes = profile_sizes;
        this.still_sizes = still_sizes;
        this.change_keys = change_keys;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getSecure_base_url() {
        return secure_base_url;
    }

    public void setSecure_base_url(String secure_base_url) {
        this.secure_base_url = secure_base_url;
    }

    public List<String> getBackdrop_sizes() {
        return backdrop_sizes;
    }

    public void setBackdrop_sizes(List<String> backdrop_sizes) {
        this.backdrop_sizes = backdrop_sizes;
    }


    public List<String> getLogo_sizes() {
        return logo_sizes;
    }

    public void setLogo_sizes(List<String> logo_sizes) {
        this.logo_sizes = logo_sizes;
    }

    public List<String> getPoster_sizes() {
        return poster_sizes;
    }

    public void setPoster_sizes(List<String> poster_sizes) {
        this.poster_sizes = poster_sizes;
    }

    public List<String> getProfile_sizes() {
        return profile_sizes;
    }

    public void setProfile_sizes(List<String> profile_sizes) {
        this.profile_sizes = profile_sizes;
    }

    public List<String> getStill_sizes() {
        return still_sizes;
    }

    public void setStill_sizes(List<String> still_sizes) {
        this.still_sizes = still_sizes;
    }

    public List<String> getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }
}
