package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piwal on 10/31/2017.
 */

public class GroupsVenues {
    @SerializedName("items")
    private List<ItemsVenues> mItemsVenuesList;

    public void setItems(List<ItemsVenues> itemsVenuesList) {
        mItemsVenuesList = itemsVenuesList;
    }

    public List<ItemsVenues> getItems() {
        return mItemsVenuesList;
    }

}
