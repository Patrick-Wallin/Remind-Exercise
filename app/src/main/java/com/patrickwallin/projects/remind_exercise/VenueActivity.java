package com.patrickwallin.projects.remind_exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.patrickwallin.projects.remind_exercise.fragment.VenueFragment;
import com.patrickwallin.projects.remind_exercise.fragment.VenuePhotoFragment;
import com.patrickwallin.projects.remind_exercise.listener.OnVenueListFragmentInteractionListener;

import timber.log.Timber;

public class VenueActivity extends AppCompatActivity implements OnVenueListFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        if(savedInstanceState != null) {}

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(getString(R.string.city_item))) {
            if(intent.hasExtra(getString(R.string.city_name)))
                setTitle(intent.getStringExtra(getString(R.string.city_name)));
            VenueFragment venueFragment = new VenueFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(getString(R.string.city_item),intent.getParcelableExtra(getString(R.string.city_item)));
            venueFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_venue_page_container, venueFragment).commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onVenueListFragmentInteraction(String venueId, String name) {
        Intent intentVenuePhotoActivity = new Intent(this, VenuePhotoActivity.class);
        intentVenuePhotoActivity.putExtra(getString(R.string.venue_id),venueId);
        intentVenuePhotoActivity.putExtra(getString(R.string.venue_name),name);
        this.startActivity(intentVenuePhotoActivity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
