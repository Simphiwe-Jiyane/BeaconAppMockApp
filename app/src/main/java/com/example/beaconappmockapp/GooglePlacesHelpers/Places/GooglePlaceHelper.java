package com.example.beaconappmockapp.GooglePlacesHelpers.Places;

public class GooglePlaceHelper {

    //TODO: Get the rating as well

    private String address;

    private String name;
    private boolean isOpen;
    private PlaceGeometry geometry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public PlaceGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(PlaceGeometry geometry) {
        this.geometry = geometry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
