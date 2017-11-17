package com.patrickwallin.projects.remind_exercise.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwallin.projects.remind_exercise.adapter.MyVenueRecyclerViewAdapter;
import com.patrickwallin.projects.remind_exercise.R;
import com.patrickwallin.projects.remind_exercise.data.CityItem;
import com.patrickwallin.projects.remind_exercise.listener.OnVenueListFragmentInteractionListener;
import com.patrickwallin.projects.remind_exercise.model.GroupsVenues;
import com.patrickwallin.projects.remind_exercise.model.ItemsVenues;
import com.patrickwallin.projects.remind_exercise.model.VenueSearchResultsResponse;
import com.patrickwallin.projects.remind_exercise.model.Venues;
import com.patrickwallin.projects.remind_exercise.rest.RetrofitApiClient;
import com.patrickwallin.projects.remind_exercise.rest.RetrofitSearchApiInterface;
import com.patrickwallin.projects.remind_exercise.utilities.NetworkUtils;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class VenueFragment extends Fragment {
    @BindView(R.id.venue_recycler_view)
    RecyclerView mVenueRecyclerView;

    private Context mContext;
    private MyVenueRecyclerViewAdapter mMyVenueRecyclerViewAdapter;

    private OnVenueListFragmentInteractionListener mListener;

    private CityItem mCityItem;

    public VenueFragment() {}

    public static VenueFragment newInstance(int columnCount) {
        VenueFragment fragment = new VenueFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            Parcelable parcelable = bundle.getParcelable(getString(R.string.city_item));
            if(parcelable != null) {
                mCityItem = Parcels.unwrap(parcelable);
            }else {
                mCityItem = null;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue_list, container, false);
        ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mVenueRecyclerView.setLayoutManager(linearLayoutManager);
        mVenueRecyclerView.setHasFixedSize(true);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mVenueRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mVenueRecyclerView.addItemDecoration(mDividerItemDecoration);

        mMyVenueRecyclerViewAdapter = new MyVenueRecyclerViewAdapter(null,mListener,mContext);
        mVenueRecyclerView.setAdapter(mMyVenueRecyclerViewAdapter);

        downloadDataBasedOnLocation(mCityItem);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMyVenueRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnVenueListFragmentInteractionListener) {
            mListener = (OnVenueListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnVenueListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void downloadDataBasedOnLocation(CityItem cityItem) {
        if(cityItem != null) {
            NetworkUtils networkUtils = new NetworkUtils(mContext);
            if (networkUtils.isNetworkConnected()) {
                RetrofitSearchApiInterface searchApiService =
                        RetrofitApiClient
                                .getSearchClient(mContext)
                                .create(RetrofitSearchApiInterface.class);
                Call<VenueSearchResultsResponse> call = searchApiService.getExploreResults(
                        cityItem.getGeocode(),
                        getString(R.string.four_square_api_version),
                        getString(R.string.limit_venues),
                        getString(R.string.show_venue_photo),
                        getString(R.string.client_id),
                        getString(R.string.client_secret));
                call.enqueue(new Callback<VenueSearchResultsResponse>() {
                    @Override
                    public void onResponse(Call<VenueSearchResultsResponse> call, Response<VenueSearchResultsResponse> response) {
                        List<GroupsVenues> groupsVenuesList = response.body().getResponse().getGroups();
                        if(groupsVenuesList != null && !groupsVenuesList.isEmpty() ) {
                            List<ItemsVenues> itemsVenuesList = groupsVenuesList.get(0).getItems();
                            if(itemsVenuesList != null && !itemsVenuesList.isEmpty()) {
                                if (mMyVenueRecyclerViewAdapter != null)
                                    mMyVenueRecyclerViewAdapter.loadData(itemsVenuesList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<VenueSearchResultsResponse> call, Throwable t) {
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
