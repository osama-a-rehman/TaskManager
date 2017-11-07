package com.example.osamamac.taskmanager.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osamamac.taskmanager.Adapters.LocationsAdapter;
import com.example.osamamac.taskmanager.Adapters.RemindersAdapter;
import com.example.osamamac.taskmanager.Database.SQLiteHandler;
import com.example.osamamac.taskmanager.Fragments.AddNewLocationFragment;
import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.Model.Location;
import com.example.osamamac.taskmanager.Model.Reminder;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddLocationsActivity extends AppCompatActivity{

    private ListView locationsListView;
    private ArrayList<Location> locationsArray;
    private FloatingActionButton addLocationButton;

    private LocationsAdapter locationsAdapter;

    private FragmentManager fragmentManager;

    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);

        setTitle("Add Locations");

        locationsListView = findViewById(R.id.addNewLocationsListView);
        addLocationButton = findViewById(R.id.addNewLocationFloatingButton);

        locationsArray = new ArrayList<>();

        db = new SQLiteHandler(this);

        final List<Location> tempLocationsList = db.getAllTemporaryLocations();

        if(tempLocationsList != null && tempLocationsList.size() > 0){
            locationsArray = (ArrayList<Location>) tempLocationsList;

            Toast.makeText(this, tempLocationsList.size() + "", Toast.LENGTH_LONG).show();
        }

        locationsAdapter = new LocationsAdapter(this, locationsArray);

        locationsListView.setAdapter(locationsAdapter);

        fragmentManager = getSupportFragmentManager();

        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewLocationFragment locationFragment = new AddNewLocationFragment(new AddNewTaskFragmentsInterface() {
                    @Override
                    public void doneSelecting(int id, String value) {
                        String[] strLocation = value.split("/");

                        double latitude = Double.parseDouble(strLocation[0]);
                        double longitude = Double.parseDouble(strLocation[1]);
                        String placeInfo = strLocation[2];

                        Log.i("Locations Activity", String.valueOf(latitude));
                        Log.i("Locations Activity", String.valueOf(longitude));
                        Log.i("Locations Activity", placeInfo);

                        Location newTempLocation = new Location(latitude, longitude, placeInfo);

                        db.addTemporaryLocation(newTempLocation);

                        locationsArray.add(newTempLocation);

                        locationsAdapter.notifyDataSetChanged();

                    }
                });

                locationFragment.show(fragmentManager, "Location Fragment");
                //locationFragment.getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Utils.LOCATIONS_RESULT_EXTRA, (locationsArray.size() > 0) ? String.valueOf(locationsArray.size()) + " Locations" : "No Locations");
        setResult(Utils.LOCATIONS_RESULT_CODE, intent);
        finish();
    }
}
