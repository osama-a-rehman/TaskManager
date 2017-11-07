package com.example.osamamac.taskmanager.Fragments;


import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.osamamac.taskmanager.Adapters.RemindersAdapter;
import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class AddNewLocationFragment extends DialogFragment implements OnMapReadyCallback{

    private AddNewTaskFragmentsInterface addNewTaskFragmentsInterface;

    private static View rootView;

    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mLocationPermissionGranted;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private GoogleMap mMap;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 18;
    private static final int WORLD_ZOOM = 2;

    private FloatingActionButton addNewPlaceFloatingButton;

    private com.example.osamamac.taskmanager.Model.Location location;


    public AddNewLocationFragment() {

    }

    public AddNewLocationFragment(AddNewTaskFragmentsInterface addNewTaskFragmentsInterface) {
        this.addNewTaskFragmentsInterface = addNewTaskFragmentsInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_add_new_location, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        // Construct a GeoDataClient.
         mGeoDataClient = Places.getGeoDataClient(getActivity(), null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(getContext(), null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        addNewPlaceFloatingButton = rootView.findViewById(R.id.addNewPlaceFloatingButton);
        addNewPlaceFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(location != null){
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    addNewTaskFragmentsInterface.doneSelecting(Utils.LOCATIONS_FRAGMENT_ID, latitude + "/" + longitude + "/" + location.getPlaceInfo());

                    dismiss();
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.addnewLocationMapFragment);
        mapFragment.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.addnewLocationPlaceAutoCompleteFragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Location Fragment", "Place: " + place.getName());

                LatLng latLng = place.getLatLng();

                location = new com.example.osamamac.taskmanager.Model.Location(latLng.latitude, latLng.longitude, (String) place.getAddress());

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));

                addNewPlaceFloatingButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Location Fragment", "An error occurred: " + status);
            }
        });

        return rootView;
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }

        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();

                            LatLng yourLocation = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourLocation, DEFAULT_ZOOM));

                            mMap.addMarker(new MarkerOptions()
                                    .position(yourLocation)
                                    .title("You're here"));
                        } else {
                            Log.d("Location Fragment", "Current location is null. Using defaults.");
                            Log.e("Location Fragment", "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, WORLD_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }

        updateLocationUI();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setOnMapLongClickListener(this);
        // Do other setup activities here too, as described elsewhere in this tutorial.

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    /*@Override
    public void onMapLongClick(LatLng latLng) {
        double latitude = latLng.latitude;
        double longitude = latLng.longitude;



        addNewTaskFragmentsInterface.doneSelecting(Utils.LOCATIONS_FRAGMENT_ID, latitude + "/" + longitude);
        dismiss();
    }*/
}
