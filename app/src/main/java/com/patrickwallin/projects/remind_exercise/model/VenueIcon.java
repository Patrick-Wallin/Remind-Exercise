package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/29/2017.
 */

public class VenueIcon {
    @SerializedName("prefix")
    private String  mPrefix;
    @SerializedName("suffix")
    private String mSuffix;

    public String getPrefix() {
        return mPrefix;
    }

    public void setPrefix(String prefix) {
        mPrefix = prefix;
    }

    public String getSuffix() {
        return mSuffix;
    }

    public void setSuffix(String suffix) {
        mSuffix = suffix;
    }
}
