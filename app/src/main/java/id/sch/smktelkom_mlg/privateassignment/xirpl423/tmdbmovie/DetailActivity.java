package id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * An activity representing a single MovieItem detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ListActivity}.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "id";
    public static final String POSTER_PATH = "poster_path";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String YEAR = "year";
    public static final String RELEASE = "release";
    public static final String SINOPSIS = "sinopsis";
    public static final String TITLE = "title";
    public static final String DURATION = "duration";
    ImageView imageView;
    int movie_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieitem_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            movie_id = getIntent().getIntExtra(DetailActivity.MOVIE_ID, 0);

            arguments.putInt(DetailActivity.MOVIE_ID,
                    getIntent().getIntExtra(DetailActivity.MOVIE_ID, 0));
            arguments.putString(DetailActivity.POSTER_PATH,
                    getIntent().getStringExtra(DetailActivity.POSTER_PATH));
            arguments.putString(DetailActivity.BACKDROP_PATH,
                    getIntent().getStringExtra(DetailActivity.BACKDROP_PATH));
            arguments.putString(DetailActivity.RELEASE,
                    getIntent().getStringExtra(DetailActivity.RELEASE));
            arguments.putString(DetailActivity.SINOPSIS,
                    getIntent().getStringExtra(DetailActivity.SINOPSIS));
            arguments.putString(DetailActivity.TITLE,
                    getIntent().getStringExtra(DetailActivity.TITLE));
            arguments.putFloat(DetailActivity.DURATION,
                    getIntent().getFloatExtra(DetailActivity.DURATION, 0));

            arguments.putString(DetailActivity.YEAR,
                    getIntent().getStringExtra(DetailActivity.YEAR));

            imageView = (ImageView) findViewById(R.id.img_background);

            if (imageView != null) {
                Glide.with(this) //
                        .load(getIntent().getStringExtra("backdrop_path")) //
                        //.placeholder(R.drawable.placeholder) //
                        //.error(R.drawable.error) //
                        //.fit() //
                        .into(imageView);
            }

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movieitem_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
