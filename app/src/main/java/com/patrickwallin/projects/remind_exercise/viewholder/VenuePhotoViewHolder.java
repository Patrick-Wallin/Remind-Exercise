package com.patrickwallin.projects.remind_exercise.viewholder;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.patrickwallin.projects.remind_exercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piwal on 10/31/2017.
 */

public class VenuePhotoViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.venue_photo_image_view)
    public ImageView mVenuePhotoImageView;

    public VenuePhotoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
