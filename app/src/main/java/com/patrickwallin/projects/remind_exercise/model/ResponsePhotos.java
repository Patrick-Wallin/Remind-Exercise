package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/31/2017.
 */

public class ResponsePhotos {
    @SerializedName("photos")
    private VenuesAllPhotos mPhotos;

    public void setPhotos(VenuesAllPhotos photos) {
        mPhotos = photos;
    }

    public VenuesAllPhotos getPhotos() {
        return mPhotos;
    }
}
