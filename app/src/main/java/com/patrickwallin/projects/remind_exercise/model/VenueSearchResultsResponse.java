package com.patrickwallin.projects.remind_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piwal on 10/29/2017.
 */

public class VenueSearchResultsResponse {
    @SerializedName("meta")
    private Meta mMeta;
    @SerializedName("response")
    private Response mResponse;

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta)  {
        mMeta = meta;
    }

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }
}
