package com.patrickwallin.projects.remind_exercise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrickwallin.projects.remind_exercise.R;
import com.patrickwallin.projects.remind_exercise.data.VenueContract;
import com.patrickwallin.projects.remind_exercise.listener.OnVenueListFragmentInteractionListener;
import com.patrickwallin.projects.remind_exercise.model.Categories;
import com.patrickwallin.projects.remind_exercise.model.GroupsPhotos;
import com.patrickwallin.projects.remind_exercise.model.ItemsPhotos;
import com.patrickwallin.projects.remind_exercise.model.ItemsVenues;
import com.patrickwallin.projects.remind_exercise.model.Location;
import com.patrickwallin.projects.remind_exercise.model.Photos;
import com.patrickwallin.projects.remind_exercise.model.VenueIcon;
import com.patrickwallin.projects.remind_exercise.model.Venues;
import com.patrickwallin.projects.remind_exercise.utilities.NetworkUtils;
import com.patrickwallin.projects.remind_exercise.viewholder.VenueViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

public class MyVenueRecyclerViewAdapter extends RecyclerView.Adapter<VenueViewHolder> {
    private List<ItemsVenues> mItemsVenuesList;
    private final OnVenueListFragmentInteractionListener mListener;
    private Context mContext;

    public MyVenueRecyclerViewAdapter(List<ItemsVenues> itemsVenuesList, OnVenueListFragmentInteractionListener listener, Context context) {
        mItemsVenuesList = itemsVenuesList;
        mListener = listener;
        mContext = context;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_venue, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VenueViewHolder holder, int position) {
        if(mItemsVenuesList != null && position < mItemsVenuesList.size()) {
            final ItemsVenues itemsVenues = mItemsVenuesList.get(position);
            if(itemsVenues != null) {
                final Venues venues = itemsVenues.getVenues();
                Photos photos = venues.getPhotos();

                boolean hasImagePath = false;

                if(photos != null) {
                    List<GroupsPhotos> groupsPhotosList = photos.getGroupsPhotos();
                    if(groupsPhotosList != null && !groupsPhotosList.isEmpty()) {
                        List<ItemsPhotos> itemsPhotosList = groupsPhotosList.get(0).getItemsPhotos();
                        if(itemsPhotosList != null && !itemsPhotosList.isEmpty()) {
                            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
                            float px = 40 * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
                            Integer pxInInt = Math.round(px);

                            String imagePath = itemsPhotosList.get(0).getFullImagePathBasedOnFixedSize(pxInInt,pxInInt);
                            hasImagePath = true;
                            Timber.d("Venue Image Path: %s",imagePath.toString());
                            Picasso.with(mContext)
                                    .load(imagePath.toString())
                                    .placeholder(R.drawable.ic_no_image_available)
                                    .into(holder.mVenueImageView);
                        }
                    }
                }

                if(hasImagePath == false) {
                    Picasso.with(mContext)
                            .load(R.drawable.ic_no_image_available)
                            .into(holder.mVenueImageView);
                }

                boolean bookmark = VenueContract.hasThisBeenBookmarked(itemsVenues.getVenues().getId(),mContext);
                Timber.d("Name: %s",venues.getName());
                Timber.d("ID: %s", venues.getId());
                Timber.d("bookmark: %s",(bookmark ? "YES" : "NO"));
                if(bookmark) {
                    holder.mVenueBookmarkImageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
                }else {
                    holder.mVenueBookmarkImageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                }

                holder.mVenueNameTextView.setText(venues.getName());
                List<Categories> categoriesList = venues.getCategories();
                if (categoriesList != null && categoriesList.size() > 0) {
                    holder.mVenueCategoriesTextView.setText(categoriesList.get(0).getName());
                }
                Location location = venues.getLocation();
                if (location != null) {
                    holder.mVenueLocationTextView.setText(location.getFullAddress());
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NetworkUtils networkUtils = new NetworkUtils(mContext);
                        if (networkUtils.isNetworkConnected()) {
                            mListener.onVenueListFragmentInteraction(itemsVenues.getVenues().getId(), venues.getName());
                        }else {
                            networkUtils.showAlertMessageAboutNoInternetConnection(false);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mItemsVenuesList != null ? mItemsVenuesList.size() : 0);
    }

    public void loadData(List<ItemsVenues> itemsVenuesList) {
        mItemsVenuesList = itemsVenuesList;
        notifyDataSetChanged();
    }
}
