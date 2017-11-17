package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/29/2017.
 */

public class Location {
    @SerializedName("address")
    private String mAddress;
    @SerializedName("postalCode")
    private String mPostalCode;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("city")
    private String mCity;
    @SerializedName("state")
    private String mState;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public void setPostalCode(String postalCode) {
        mPostalCode = postalCode;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getFullAddress() {
        StringBuilder sbFullAddress = new StringBuilder();
        boolean hasCity = false;
        boolean hasState = false;

        if(mAddress != null && !mAddress.trim().isEmpty())
            sbFullAddress.append(mAddress.trim());
        if(mCity != null && !mCity.trim().isEmpty()) {
            hasCity = true;
            if(!sbFullAddress.toString().isEmpty())
                sbFullAddress.append(System.getProperty("line.separator"));
            sbFullAddress.append(mCity.trim());
        }
        if(mState != null && !mState.trim().isEmpty()) {
            hasState = true;
            if(!sbFullAddress.toString().isEmpty()) {
                sbFullAddress.append((hasCity ? ", " : System.getProperty("line.separator")));
            }
            sbFullAddress.append(mState.trim());
        }
        if(mPostalCode != null && !mPostalCode.trim().isEmpty()) {
            if(!sbFullAddress.toString().isEmpty()) {
                sbFullAddress.append((hasCity || hasState ? ", " : System.getProperty("line.separator")));
            }
            sbFullAddress.append(mPostalCode.trim());
        }
        if(mCountry != null && !mCountry.trim().isEmpty()) {
            if(!sbFullAddress.toString().isEmpty())
                sbFullAddress.append(System.getProperty("line.separator"));
            sbFullAddress.append(mCountry.trim());
        }

        return sbFullAddress.toString();
    }
}
