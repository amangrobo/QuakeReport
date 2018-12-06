package com.grobo.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ArrayList<Quake> earthquakes = QueryUtils.extractEarthquakes();

        ListView quakeListView = (ListView) findViewById(R.id.quake_list_view);

        // Create a new {@link ArrayAdapter} of earthquakes
        ListItemAdapter adapter = new ListItemAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        quakeListView.setAdapter(adapter);


    }
}
