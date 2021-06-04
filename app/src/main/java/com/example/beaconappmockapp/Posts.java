package com.example.beaconappmockapp;



public class Posts {
    private String Name;// These are to be replaced by the actually row names of the api data for locations
    private String Location;

    public Posts(String name, String location) {
        Name = name;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }
}
