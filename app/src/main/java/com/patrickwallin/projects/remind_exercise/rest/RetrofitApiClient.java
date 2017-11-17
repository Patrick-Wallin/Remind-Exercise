package com.patrickwallin.projects.remind_exercise.rest;

import android.content.Context;

import com.patrickwallin.projects.remind_exercise.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by piwal on 10/29/2017.
 */

public class RetrofitApiClient {
    public static Retrofit getSearchClient(Context context) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_search_venue_url_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
