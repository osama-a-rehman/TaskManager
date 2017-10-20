package com.example.osamamac.taskmanager.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.osamamac.taskmanager.Fragments.AddNewLocationFragment;
import com.example.osamamac.taskmanager.Fragments.AddNewTaskProjectFragment;
import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;

public class AddLocationsActivity extends AppCompatActivity{

    private ListView locationsListView;
    private FloatingActionButton addLocationButton;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);

        locationsListView = (ListView) findViewById(R.id.addNewLocationsListView);
        addLocationButton = (FloatingActionButton) findViewById(R.id.addNewLocationFloatingButton);

        fragmentManager = getSupportFragmentManager();

        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewLocationFragment locationFragment = new AddNewLocationFragment(new AddNewTaskFragmentsInterface() {
                    @Override
                    public void doneSelecting(int id, String value) {
                        // Add New Location
                    }
                });

                locationFragment.show(fragmentManager, "Location Fragment");
            }
        });
    }

}
