package com.example.beaconappmockapp.NetworkHelpers;

//Create directions variables


import com.example.beaconappmockapp.GoogleDirectionsHelpers.RoutesHelper;
import com.example.beaconappmockapp.GooglePlacesHelpers.Places.GooglePlaceHelper;
import com.google.android.gms.maps.model.LatLng;

public class DirectionsHelper {
    //private String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyBSXbvQ6E_PPDHtjtiIke1fOwM0PpkUekI";


    //Construct url
    private String createUrl(LatLng origin, LatLng dest){

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+origin.latitude+","+origin.longitude+"&destination="+dest.latitude+","+dest.longitude+"&key=AIzaSyBSXbvQ6E_PPDHtjtiIke1fOwM0PpkUekI";

        return url;
    }

    //send an http request for json directions data and get a response
    public String getResponse(String url){
        NetworkUtil helper = new NetworkUtil(url);
        String response = helper.GetJsonResponse();
        return response;
    }

    //TODO: Create Directions object and parse the JSON data

    public RoutesHelper GetDirections(LatLng origin, LatLng dest){

        String response = getResponse(createUrl(origin,dest));

        //TODO: Break down the JSON response and return it to where it was called

        return null;
    }

}
