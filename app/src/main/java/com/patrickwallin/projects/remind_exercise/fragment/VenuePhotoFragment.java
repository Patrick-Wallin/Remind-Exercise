package com.patrickwallin.projects.remind_exercise.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwallin.projects.remind_exercise.adapter.MyVenuePhotoRecyclerViewAdapter;
import com.patrickwallin.projects.remind_exercise.R;
import com.patrickwallin.projects.remind_exercise.model.ItemsPhotos;
import com.patrickwallin.projects.remind_exercise.model.ResponsePhotos;
import com.patrickwallin.projects.remind_exercise.model.VenuePhotoResultsResponse;
import com.patrickwallin.projects.remind_exercise.model.VenuesAllPhotos;
import com.patrickwallin.projects.remind_exercise.rest.RetrofitApiClient;
import com.patrickwallin.projects.remind_exercise.rest.RetrofitSearchApiInterface;
import com.patrickwallin.projects.remind_exercise.utilities.NetworkUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class VenuePhotoFragment extends Fragment {
    @BindView(R.id.venue_photo_recycler_view)
    public RecyclerView mVenuePhotoRecyclerView;

    private String mVenueId;
    private Context mContext;
    private MyVenuePhotoRecyclerViewAdapter mMyVenuePhotoRecyclerViewAdapter;
    private int numberOfColumn = 2;

    public VenuePhotoFragment() {}

    public static VenuePhotoFragment newInstance(int columnCount) {
        VenuePhotoFragment fragment = new VenuePhotoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments() != null) {
                Bundle bundle = getArguments();
                mVenueId = bundle.getString(getString(R.string.venue_id));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venuephoto_list, container, false);
        ButterKnife.bind(this,view);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            numberOfColumn = 3;
        }
        else {
            numberOfColumn = 2;
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(numberOfColumn,StaggeredGridLayoutManager.VERTICAL);
        mVenuePhotoRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mMyVenuePhotoRecyclerViewAdapter = new MyVenuePhotoRecyclerViewAdapter(null,mContext);
        mVenuePhotoRecyclerView.setAdapter(mMyVenuePhotoRecyclerViewAdapter);

        downloadDataBasedOnVenueId(mVenueId);

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void downloadDataBasedOnVenueId(String venueId) {
        if(venueId != null && !venueId.isEmpty()) {
            NetworkUtils networkUtils = new NetworkUtils(mContext);
            if (networkUtils.isNetworkConnected()) {
                RetrofitSearchApiInterface searchApiService =
                        RetrofitApiClient
                                .getSearchClient(mContext)
                                .create(RetrofitSearchApiInterface.class);
                Call<VenuePhotoResultsResponse> call = searchApiService.getPhotosResults(
                        venueId,
                        getString(R.string.four_square_api_version),
                        getString(R.string.limit_venues),
                        getString(R.string.client_id),
                        getString(R.string.client_secret));

                call.enqueue(new Callback<VenuePhotoResultsResponse>() {
                    @Override
                    public void onResponse(Call<VenuePhotoResultsResponse> call, Response<VenuePhotoResultsResponse> response) {
                        ResponsePhotos responsePhotos = response.body().getResponsePhotos();
                        if(responsePhotos != null) {
                            VenuesAllPhotos venuesAllPhotos = responsePhotos.getPhotos();
                            if(venuesAllPhotos != null) {
                                List<ItemsPhotos> itemsPhotosList = venuesAllPhotos.getItemsPhotos();
                                if(itemsPhotosList != null && !itemsPhotosList.isEmpty()) {
                                    if(mMyVenuePhotoRecyclerViewAdapter != null)
                                        mMyVenuePhotoRecyclerViewAdapter.loadData(itemsPhotosList);

                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<VenuePhotoResultsResponse> call, Throwable t) {
                        NetworkUtils networkUtils = new NetworkUtils(mContext);
                        networkUtils.showAlertMessageBasedOnErrorMessage(t.getMessage());
                    }
                });

            } else {
                networkUtils.showAlertMessageAboutNoInternetConnection(false);
            }
        }
    }
}
