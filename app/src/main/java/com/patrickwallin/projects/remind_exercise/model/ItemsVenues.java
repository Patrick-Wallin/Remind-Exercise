package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piwal on 10/31/2017.
 */

public class ItemsVenues {
    @SerializedName("venue")
    private Venues mVenues;

    public Venues getVenues() {
        return mVenues;
    }

    public void setVenues(Venues venues) {
        mVenues = venues;
    }
}
