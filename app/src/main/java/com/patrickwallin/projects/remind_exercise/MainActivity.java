package com.patrickwallin.projects.remind_exercise;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.patrickwallin.projects.remind_exercise.data.CityItem;
import com.patrickwallin.projects.remind_exercise.fragment.CityFragment;
import com.patrickwallin.projects.remind_exercise.listener.OnCityListFragmentInteractionListener;

import org.parceler.Parcels;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements OnCityListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        loadCityFragment();
    }

    private void loadCityFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.city_frame_layout, new CityFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onCityListFragmentInteraction(CityItem item, String name) {
        Intent intentVenueActivity = new Intent(this, VenueActivity.class);
        intentVenueActivity.putExtra(getString(R.string.city_item),Parcels.wrap(item));
        intentVenueActivity.putExtra(getString(R.string.city_name),name);
        this.startActivity(intentVenueActivity);
    }
}
