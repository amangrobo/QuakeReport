package com.grobo.quakereport;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListItemAdapter extends ArrayAdapter {

    public ListItemAdapter(Activity context, ArrayList<String> input){
        super(context,0, input);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        return listItemView;
    }
}
