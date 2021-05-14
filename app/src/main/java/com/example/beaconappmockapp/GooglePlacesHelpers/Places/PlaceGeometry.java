package com.example.beaconappmockapp.GooglePlacesHelpers.Places;

public class PlaceGeometry {

    public PlaceGeometry(PlacesLocation location, PlaceViewport viewport) {
        this.location = location;
        this.viewport = viewport;
    }

    private PlacesLocation location;
    private PlaceViewport viewport;

    public PlacesLocation getLocation() {
        return location;
    }

    public PlaceViewport getViewport() {
        return viewport;
    }


}
