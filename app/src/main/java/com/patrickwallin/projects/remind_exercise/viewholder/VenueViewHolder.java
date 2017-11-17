package com.patrickwallin.projects.remind_exercise.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.patrickwallin.projects.remind_exercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piwal on 10/28/2017.
 */

public class VenueViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.venue_image_view) public ImageView mVenueImageView;
    @BindView(R.id.venue_name_text_view) public TextView mVenueNameTextView;
    @BindView(R.id.venue_location_text_view) public TextView mVenueLocationTextView;
    @BindView(R.id.venue_categories_text_view) public TextView mVenueCategoriesTextView;
    @BindView(R.id.venue_bookmark_image_view) public ImageView mVenueBookmarkImageView;

    public VenueViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
