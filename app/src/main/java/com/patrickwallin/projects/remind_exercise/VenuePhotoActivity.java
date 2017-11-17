package com.patrickwallin.projects.remind_exercise;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.patrickwallin.projects.remind_exercise.data.VenueContract;
import com.patrickwallin.projects.remind_exercise.fragment.VenuePhotoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class VenuePhotoActivity extends AppCompatActivity {
    @BindView(R.id.venue_photo_floating_action_button)
    public FloatingActionButton mVenuePhotoFloatingActionButton;

    private String mVenueId;
    private boolean mBookmark;
    private ContentValues mContentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_photo);

        ButterKnife.bind(this);

        mBookmark = false;

        if(savedInstanceState != null) {
        }

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(getString(R.string.venue_id))) {
            if(intent.hasExtra(getString(R.string.venue_name)))
                setTitle(intent.getStringExtra(getString(R.string.venue_name)));
            Timber.d("Call Venue Photo Fragment with venue id");
            VenuePhotoFragment venuePhotoFragment = new VenuePhotoFragment();
            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.venue_id),intent.getStringExtra(getString(R.string.venue_id)));
            venuePhotoFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_venue_photo_page_container, venuePhotoFragment).commit();

            mVenueId = intent.getStringExtra(getString(R.string.venue_id));

            mContentValues = new ContentValues();
            mContentValues.put(VenueContract.VenueBookmarkEntry.COLUMN_VENUE_BOOKMARK_ID, mVenueId);

            Timber.d("ID: %s", mVenueId);
            mBookmark = VenueContract.hasThisBeenBookmarked(mVenueId,VenuePhotoActivity.this);

            if(mBookmark) {
                mVenuePhotoFloatingActionButton.setImageResource(R.drawable.ic_bookmark_black_24dp);
            }else {
                mVenuePhotoFloatingActionButton.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            }

            mVenuePhotoFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Timber.d("you click on floating button: %s",mVenueId);
                    mBookmark = !mBookmark;
                    if(mBookmark) {
                        mVenuePhotoFloatingActionButton.setImageResource(R.drawable.ic_bookmark_black_24dp);
                        VenuePhotoActivity.this.getContentResolver().insert(VenueContract.VenueBookmarkEntry.CONTENT_URI,mContentValues);
                    }else {
                        mVenuePhotoFloatingActionButton.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                        String sqlWhereDelete = VenueContract.VenueBookmarkEntry.COLUMN_VENUE_BOOKMARK_ID + " = " + android.database.DatabaseUtils.sqlEscapeString(mVenueId);
                        int numberDeleted = VenuePhotoActivity.this.getContentResolver().delete(VenueContract.VenueBookmarkEntry.CONTENT_URI,sqlWhereDelete,null);
                    }

                }
            });
        }
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
