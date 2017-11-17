package com.patrickwallin.projects.remind_exercise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwallin.projects.remind_exercise.data.CityItem;
import com.patrickwallin.projects.remind_exercise.R;
import com.patrickwallin.projects.remind_exercise.listener.OnCityListFragmentInteractionListener;
import com.patrickwallin.projects.remind_exercise.utilities.NetworkUtils;
import com.patrickwallin.projects.remind_exercise.viewholder.CityViewHolder;

import java.util.List;

import timber.log.Timber;

public class MyCityRecyclerViewAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private List<CityItem> mCityList;
    private final OnCityListFragmentInteractionListener mListener;
    private Context mContext;

    public MyCityRecyclerViewAdapter(List<CityItem> items, OnCityListFragmentInteractionListener listener, Context context) {
        mCityList = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CityViewHolder holder, final int position) {
        holder.mCityNameTextView.setText(mCityList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mListener) {
                    NetworkUtils networkUtils = new NetworkUtils(mContext);
                    if (networkUtils.isNetworkConnected()) {
                        Timber.d("Fire listener: %s",mCityList.get(position).getName());
                        mListener.onCityListFragmentInteraction(mCityList.get(position),mCityList.get(position).getName());
                    }else {
                        networkUtils.showAlertMessageAboutNoInternetConnection(false);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mCityList != null ? mCityList.size() : 0);
    }

}
