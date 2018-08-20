package com.astuter.capstone.gui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuter.capstone.R;
import com.astuter.capstone.config.Config;
import com.bumptech.glide.Glide;

/**
 * An activity representing a single Place detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PlaceListActivity}.
 */
public class PlaceDetailActivity extends AppCompatActivity{
    private String vicinity;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(mToolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            vicinity = bundle.getString(Config.KEY_PLACE_VICINITY);

            ImageView placePhoto = (ImageView) findViewById(R.id.place_photo);
            Glide.with(placePhoto.getContext())
                    .load(Config.getPlacePhotoUrl("" + Config.dpToPx(this, 320), bundle.getString(Config.KEY_PLACE_PHOTO), Config.API_KEY))
                    .into(placePhoto);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction.
            PlaceDetailFragment fragment = new PlaceDetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.place_detail_container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
//            NavUtils.navigateUpTo(this, new Intent(this, PlaceListActivity.class));
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void fabClicked(View v){
        //Toast.makeText(this, "This is my Toast message!",
            //    Toast.LENGTH_LONG).show();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,

            //    Uri.parse("http://maps.google.com/maps?daddr=20.5666,45.345"));


        //Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q="+vicinity));
        startActivity(intent);
    }
}
