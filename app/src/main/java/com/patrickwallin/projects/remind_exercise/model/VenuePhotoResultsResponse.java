package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/31/2017.
 */

public class VenuePhotoResultsResponse {
    @SerializedName("meta")
    private Meta mMeta;
    @SerializedName("response")
    private ResponsePhotos mResponsePhotos;

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta)  {
        mMeta = meta;
    }

    public ResponsePhotos getResponsePhotos() {
        return mResponsePhotos;
    }

    public void setResponsePhotos(ResponsePhotos responsePhotos) {
        mResponsePhotos = responsePhotos;
    }
}
