package com.example.osamamac.taskmanager.Model;

public class Location {
    private double latitude;
    private double longitude;
    private String placeInfo;

    public Location(double latitude, double longitude, String placeInfo) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeInfo = placeInfo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }
}
