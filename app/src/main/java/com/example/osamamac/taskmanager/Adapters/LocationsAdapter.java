package com.example.osamamac.taskmanager.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.osamamac.taskmanager.Model.Location;
import com.example.osamamac.taskmanager.R;


import java.util.ArrayList;



public class LocationsAdapter extends ArrayAdapter<Location> {
    public LocationsAdapter(Context context, ArrayList<Location> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if(currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.location_list_item, parent, false);
        }

        Location location = getItem(position);

        TextView locationPlace = currentView.findViewById(R.id.locationsListItemPlace);
        locationPlace.setText(location.getPlaceInfo());

        return currentView;
    }
}
