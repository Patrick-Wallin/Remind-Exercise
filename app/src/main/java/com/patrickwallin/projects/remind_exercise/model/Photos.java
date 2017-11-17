package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by piwal on 10/31/2017.
 */

public class Photos {
    @SerializedName("count")
    private Integer mCount;
    @SerializedName("groups")
    private List<GroupsPhotos> mGroupsPhotosList;

    public Integer getCount() {
        return mCount;
    }

    public void setCount(Integer count) {
        mCount = count;
    }

    public List<GroupsPhotos> getGroupsPhotos() {
        return mGroupsPhotosList;
    }

    public void setGroupsPhotos(List<GroupsPhotos> groupsPhotosList) {
        mGroupsPhotosList = groupsPhotosList;
    }
}
