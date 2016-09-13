package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 9/13/16.
 */
public class ImagePersons {
    @SerializedName("id")
    private String id;
    @SerializedName("profiles")
    private List<ImagePerson> profiles;

    public ImagePersons(String id, List<ImagePerson> profiles) {
        this.id = id;
        this.profiles = profiles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImagePerson> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ImagePerson> profiles) {
        this.profiles = profiles;
    }
}
