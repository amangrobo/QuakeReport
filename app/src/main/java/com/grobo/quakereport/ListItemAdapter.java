package com.grobo.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends ArrayAdapter<Quake> {

    public ListItemAdapter(Activity context, ArrayList<Quake> earthquakes){
        super(context,0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Quake currentEarthquake = getItem(position);

        TextView quakeMagnitudeView = (TextView) listItemView.findViewById(R.id.quake_magnitude);
        quakeMagnitudeView.setText(currentEarthquake.getMagnitude());

        TextView quakeLocationView = (TextView) listItemView.findViewById(R.id.quake_location);
        quakeLocationView.setText(currentEarthquake.getLocation());

        TextView quakeDateView = (TextView) listItemView.findViewById(R.id.quake_date);
        quakeDateView.setText(currentEarthquake.getQuakeDate());

        return listItemView;
    }
}
