package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piwal on 10/31/2017.
 */

public class VenuesAllPhotos {
    @SerializedName("count")
    private Integer mCount;
    @SerializedName("items")
    private List<ItemsPhotos> mItemsPhotosList;

    public Integer getCount() {
        return mCount;
    }

    public void setCount(Integer count) {
        mCount = count;
    }

    public List<ItemsPhotos> getItemsPhotos() {
        return mItemsPhotosList;
    }

    public void setItemsPhotos(List<ItemsPhotos> itemsPhotosList) {
        mItemsPhotosList = itemsPhotosList;
    }
}
