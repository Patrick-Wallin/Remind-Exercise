package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/29/2017.
 */

public class Categories {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("icon")
    private VenueIcon mVenueIcon;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public VenueIcon getVenueIcon() {
        return mVenueIcon;
    }

    public void setVenueIcon(VenueIcon venueIcon) {
        mVenueIcon = venueIcon;
    }

}
