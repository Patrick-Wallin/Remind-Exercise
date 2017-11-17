package com.patrickwallin.projects.remind_exercise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwallin.projects.remind_exercise.R;
import com.patrickwallin.projects.remind_exercise.model.ItemsPhotos;
import com.patrickwallin.projects.remind_exercise.viewholder.VenuePhotoViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

public class MyVenuePhotoRecyclerViewAdapter extends RecyclerView.Adapter<VenuePhotoViewHolder> {
    private List<ItemsPhotos> mItemsPhotosList;
    private Context mContext;

    public MyVenuePhotoRecyclerViewAdapter(List<ItemsPhotos> items, Context context) {
        mItemsPhotosList = items;
        mContext = context;
    }

    @Override
    public VenuePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_venuephoto, parent, false);
        return new VenuePhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VenuePhotoViewHolder holder, int position) {
        if(mItemsPhotosList != null && position < mItemsPhotosList.size()) {
            String imagePath = mItemsPhotosList.get(position).getFullImagePath();

            boolean hasImagePath = false;

            if(imagePath != null && !imagePath.isEmpty()) {
                hasImagePath = true;
                Timber.d("Venue Photo Grid Path: %s",imagePath.toString());
                Picasso.with(mContext)
                        .load(imagePath.toString())
                        .placeholder(R.drawable.ic_no_image_available)
                        .into(holder.mVenuePhotoImageView);
            }

            if(hasImagePath == false) {
                Picasso.with(mContext)
                        .load(R.drawable.ic_no_image_available)
                        .into(holder.mVenuePhotoImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mItemsPhotosList != null ? mItemsPhotosList.size() : 0);
    }

    public void loadData(List<ItemsPhotos> itemsPhotosList) {
        mItemsPhotosList = itemsPhotosList;
        notifyDataSetChanged();
    }

}
