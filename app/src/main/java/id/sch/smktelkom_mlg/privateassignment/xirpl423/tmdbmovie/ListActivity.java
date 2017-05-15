package id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.Adapter.GridView;
import id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.Model.Hasil;

public class ListActivity extends AppCompatActivity {

    android.widget.GridView gv;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieitem_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Pupular Movies");
        }

        gv = (android.widget.GridView) findViewById(R.id.movieitem_list);
        assert gv != null;

        FetchMovies fetchMovies = new FetchMovies();
        String url = "http://api.themoviedb.org/3/movie/popular";
        fetchMovies.execute(url);

        if (findViewById(R.id.movieitem_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.

            mTwoPane = true;
        }
    }

    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public class FetchMovies extends AsyncTask<String, Void, List<Hasil>> {
        final ProgressDialog progressDialog = new ProgressDialog(ListActivity.this);
        private final String LOG_TAG = FetchMovies.class.getSimpleName();

        @Override
        protected void onPreExecute() {

            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading movies...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected List<Hasil> doInBackground(String... params) {
            List<Hasil> data = new ArrayList<>();

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonResult = "";
            try {
                final String BASE_URL = params[0];

                final String API_KEY = "?api_key=19ab05665da519d2289a688ae9921bdc";
                Uri builtUri = Uri.parse(BASE_URL + API_KEY).buildUpon()
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI " + builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonResult = buffer.toString();
                JSONObject result = new JSONObject(jsonResult);


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    JSONArray data_json = result.getJSONArray("results");
                    for (int i = 0; i < data_json.length(); i++) {
                        Hasil hasil = new Hasil();
                        JSONObject object = data_json.getJSONObject(i);

                        hasil.setId(object.getInt("id"));
                        hasil.setPoster_path("http://image.tmdb.org/t/p/w185" + object.getString("poster_path"));
                        hasil.setBackdrop_path("http://image.tmdb.org/t/p/w780" + object.getString("backdrop_path"));
                        hasil.setRelease_date(object.getString("release_date"));
                        hasil.setTitle(object.getString("title"));
                        hasil.setOverview(object.getString("overview"));
                        hasil.setVote_average(object.getLong("vote_average"));
                        data.add(hasil);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(final List<Hasil> movies) {
            super.onPostExecute(movies);
            progressDialog.hide();
            GridView gridView = new GridView(getApplicationContext(), movies);
            gv.setAdapter(gridView);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Hasil hasil = movies.get(position);
                    if (mTwoPane) {

                        Bundle arguments = new Bundle();
                        arguments.putString("poster_path", hasil.getPoster_path());
                        arguments.putString("backdrop_path", hasil.getBackdrop_path());
                        arguments.putString("year", hasil.getRelease_date());
                        arguments.putString("release", hasil.getRelease_date());
                        arguments.putString("sinopsis", hasil.getOverview());
                        arguments.putString("title", hasil.getTitle());
                        arguments.putFloat("duration", hasil.getVote_average());
                        arguments.putInt("id", hasil.getID());
                        DetailFragment fragment = new DetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.movieitem_detail_container, fragment)
                                .commit();
                    } else {
                        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                        intent.putExtra("poster_path", hasil.getPoster_path());
                        intent.putExtra("backdrop_path", hasil.getBackdrop_path());
                        intent.putExtra("year", hasil.getRelease_date());
                        intent.putExtra("release", hasil.getRelease_date());
                        intent.putExtra("sinopsis", hasil.getOverview());
                        intent.putExtra("title", hasil.getTitle());
                        intent.putExtra("duration", hasil.getVote_average());
                        intent.putExtra("id", hasil.getID());

                        startActivity(intent);
                    }


                }
            });
        }
    }
}
