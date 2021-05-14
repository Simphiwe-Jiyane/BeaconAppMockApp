package com.example.beaconappmockapp.NetworkHelpers;

//Create directions variables


public class DirectionsHelper {
    private String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyBSXbvQ6E_PPDHtjtiIke1fOwM0PpkUekI";
    String response;

    //send an http request for json directions data and get a response
    public void getResponse(){
        NetworkUtil helper = new NetworkUtil(url);
        this.response = helper.GetJsonResponse();
    }

    //TODO: Create Directions object and parse the JSON data

}
