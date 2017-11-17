package com.patrickwallin.projects.remind_exercise.rest;

import com.patrickwallin.projects.remind_exercise.model.VenuePhotoResultsResponse;
import com.patrickwallin.projects.remind_exercise.model.VenueSearchResultsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by piwal on 10/29/2017.
 */

public interface RetrofitSearchApiInterface {
    @GET("venues/explore")
    Call<VenueSearchResultsResponse> getExploreResults(
            @Query("ll") String searchLocationInput,
            @Query("v") String version,
            @Query("limit") String limit,
            @Query("venuePhotos") String venuePhotos,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret);
    @GET("venues/{venueId}/photos")
    Call<VenuePhotoResultsResponse> getPhotosResults(
            @Path("venueId") String venueId,
            @Query("v") String version,
            @Query("limit") String limit,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret);
}
