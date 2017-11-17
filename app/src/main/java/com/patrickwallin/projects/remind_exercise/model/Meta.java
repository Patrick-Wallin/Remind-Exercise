package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piwal on 10/29/2017.
 */

public class Meta {
    @SerializedName("code")
    private Integer mCode;
    @SerializedName("requestId")
    private String mRequestId;

    public Integer getCode() {
        return mCode;
    }

    public void setCode(Integer code) {
        mCode = code;
    }

    public String getRequestId() {
        return mRequestId;
    }

    public void setRequestId(String requestId) {
        mRequestId = requestId;
    }
}
