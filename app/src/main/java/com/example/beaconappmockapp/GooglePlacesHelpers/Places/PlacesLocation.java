package com.example.beaconappmockapp.GooglePlacesHelpers.Places;

public class PlacesLocation {

    double latitude;
    double longitude;

    public PlacesLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }


}
