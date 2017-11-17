package com.patrickwallin.projects.remind_exercise.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.patrickwallin.projects.remind_exercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piwal on 10/27/2017.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.city_name_text_view) public TextView mCityNameTextView;

    public CityViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
