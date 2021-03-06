package com.grobo.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListItemAdapter extends ArrayAdapter<Quake> {

    private static final String LOCATION_SEPARATOR = " of ";

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
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        quakeMagnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) quakeMagnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor((int) Math.floor(currentEarthquake.getMagnitude()));
        magnitudeCircle.setColor(magnitudeColor);

        String primaryLocation;
        String locationOffset;
        String stringObject = currentEarthquake.getLocation();
        if (stringObject.contains(LOCATION_SEPARATOR)) {
            String[] parts = stringObject.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = stringObject;
        }

        TextView quakeLocationView = (TextView) listItemView.findViewById(R.id.quake_location);
        quakeLocationView.setText(primaryLocation);

        TextView quakeOffsetView = (TextView) listItemView.findViewById(R.id.quake_offset);
        quakeOffsetView.setText(locationOffset);

        Date dateObject = new Date(currentEarthquake.getQuakeTimeMilisecs());
        String dateToDisplay = formatDate(dateObject);
        String timeToDisplay = formatTime(dateObject);

        TextView quakeDateView = (TextView) listItemView.findViewById(R.id.quake_date);
        quakeDateView.setText(dateToDisplay);

        TextView quakeTimeView = (TextView) listItemView.findViewById(R.id.quake_time);
        quakeTimeView.setText(timeToDisplay);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        return magnitudeFormatter.format(magnitude);
    }

    private Integer getMagnitudeColor(int magnitude) {
        int magnitudeColor;
        switch (magnitude){
            case 0:
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magnitudeColor;
    }

}
