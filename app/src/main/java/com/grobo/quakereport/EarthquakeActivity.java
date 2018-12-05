package com.grobo.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Quake> earthquakes = new ArrayList<Quake>();
        earthquakes.add(new Quake("7.2","San Francisco", "Feb 2, 2016"));
        earthquakes.add(new Quake("6.5", "London", "October 20, 2015"));
        earthquakes.add(new Quake("7.1", "Tokyo", "November 8, 2016"));
        earthquakes.add(new Quake("6.8", "Mexico City", "September 15, 2016"));
        earthquakes.add(new Quake("5.5", "Moscow", "July 5, 2016"));
        earthquakes.add(new Quake("6.2", "Rio de Janeiro", "August 19, 2016"));
        earthquakes.add(new Quake("6.5", "Paris", "March 5, 2016"));

        ListView quakeListView = (ListView) findViewById(R.id.quake_list_view);

        // Create a new {@link ArrayAdapter} of earthquakes
        ListItemAdapter adapter = new ListItemAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        quakeListView.setAdapter(adapter);


    }
}
