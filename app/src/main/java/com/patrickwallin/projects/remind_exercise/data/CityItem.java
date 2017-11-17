package com.patrickwallin.projects.remind_exercise.data;

import org.parceler.Parcel;

/**
 * Created by piwal on 10/28/2017.
 */
@Parcel
public class CityItem {
    public String mId;
    public String mName;
    public String mGeocode;

    public CityItem() {}

    public CityItem(String id, String name, String geocode) {
        mId = id;
        mName = name;
        mGeocode = geocode;
    }

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

    public String getGeocode() {
        return mGeocode;
    }

    public void setGeocode(String geocode) {
        mGeocode = geocode;
    }
}
