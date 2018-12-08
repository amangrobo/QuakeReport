package com.grobo.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Quake>> {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=100";

    private ListItemAdapter mAdapter;
    private TextView emptyView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView quakeListView = (ListView) findViewById(R.id.quake_list_view);
        emptyView = (TextView) findViewById(R.id.empty_view);
        quakeListView.setEmptyView(emptyView);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new ListItemAdapter(this, new ArrayList<Quake>());

        quakeListView.setAdapter(mAdapter);

        quakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quake currentEarthquake = mAdapter.getItem(position);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentEarthquake.getUrl()));
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null;

        if (isConnected) {
            LoaderManager loaderManager = android.support.v4.app.LoaderManager.getInstance(this);
            loaderManager.initLoader(1, null, this);
        } else {
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<Quake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoaderReset(Loader<List<Quake>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<Quake>> loader, List<Quake> quakes) {
        mAdapter.clear();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        emptyView.setText(R.string.no_data);

        if (quakes != null && !quakes.isEmpty()){
            mAdapter.addAll(quakes);
        }

    }

}
