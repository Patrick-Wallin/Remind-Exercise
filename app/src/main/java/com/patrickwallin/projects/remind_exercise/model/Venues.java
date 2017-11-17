package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piwal on 10/29/2017.
 */

public class Venues {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("location")
    private Location mLocation;
    @SerializedName("categories")
    private List<Categories> mCategoriesList;
    @SerializedName("photos")
    private Photos mPhotos;

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

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public List<Categories> getCategories() {
        return mCategoriesList;
    }

    public void setCategories(List<Categories> categoriesList) {
        mCategoriesList = categoriesList;
    }

    public Photos getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photos photos) {
        mPhotos = photos;
    }
}
