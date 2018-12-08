package com.grobo.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import static com.grobo.quakereport.QueryUtils.doEverything;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    private ListItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView quakeListView = (ListView) findViewById(R.id.quake_list_view);

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

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute();
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Quake>> {

        @Override
        protected List<Quake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Quake> listEarthquake = doEverything(urls[0]);
            return  listEarthquake;
        }

        @Override
        protected void onPostExecute(List<Quake> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()){
                mAdapter.addAll(data);
            }

        }

    }
}
