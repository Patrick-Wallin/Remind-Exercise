package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piwal on 10/29/2017.
 */

public class Response {
    @SerializedName("groups")
    private List<GroupsVenues> mGroupsVenuesList;

    public List<GroupsVenues> getGroups() {
        return mGroupsVenuesList;
    }

    public void setGroups(List<GroupsVenues> groupsVenuesList) {
        mGroupsVenuesList = groupsVenuesList;
    }
}
