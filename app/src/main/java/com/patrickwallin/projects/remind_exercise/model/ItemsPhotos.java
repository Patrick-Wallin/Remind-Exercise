package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/31/2017.
 */

public class ItemsPhotos {
    @SerializedName("prefix")
    private String mPrefix;
    @SerializedName("suffix")
    private String mSuffix;
    @SerializedName("width")
    private Integer mWidth;
    @SerializedName("height")
    private Integer mHeight;

    public void setPrefix(String prefix) {
        mPrefix = prefix;
    }

    public String getPrefix() {
        return mPrefix;
    }

    public void setSuffix(String suffix) {
        mSuffix = suffix;
    }

    public String getSuffix() {
        return mSuffix;
    }

    public void setWidth(Integer width) {
        mWidth = width;
    }

    public Integer getWidth() {
        return mWidth;
    }

    public void setHeight(Integer height) {
        mHeight = height;
    }

    public Integer getHeight() {
        return mHeight;
    }

    public String getFullImagePath() {
        StringBuilder fullImagePath = new StringBuilder();

        if(mPrefix != null && !mPrefix.isEmpty())
            fullImagePath.append(mPrefix.trim());
        if(mWidth != null && mWidth > 0) {
            if(mHeight != null && mHeight > 0) {
                fullImagePath.append(String.valueOf(mWidth).trim());
                fullImagePath.append("x");
                fullImagePath.append(String.valueOf(mHeight).trim());
            }
        }
        if(mSuffix != null && !mSuffix.isEmpty())
            fullImagePath.append(mSuffix.trim());

        return fullImagePath.toString();
    }

    public String getFullImagePathBasedOnFixedSize(Integer width, Integer height) {
        StringBuilder fullImagePath = new StringBuilder();

        if(mPrefix != null && !mPrefix.isEmpty())
            fullImagePath.append(mPrefix.trim());
        fullImagePath.append(String.valueOf(width).trim());
        fullImagePath.append("x");
        fullImagePath.append(String.valueOf(height).trim());
        if(mSuffix != null && !mSuffix.isEmpty())
            fullImagePath.append(mSuffix.trim());

        return fullImagePath.toString();
    }
}
