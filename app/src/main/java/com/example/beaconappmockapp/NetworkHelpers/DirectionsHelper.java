package com.example.beaconappmockapp.NetworkHelpers;

//Create directions variables


import com.example.beaconappmockapp.GoogleDirectionsHelpers.RouteBounds;
import com.example.beaconappmockapp.GoogleDirectionsHelpers.RouteLeg;
import com.example.beaconappmockapp.GoogleDirectionsHelpers.RouteStep;
import com.example.beaconappmockapp.GoogleDirectionsHelpers.RoutesModel;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;

public class DirectionsHelper {
    //private String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyBSXbvQ6E_PPDHtjtiIke1fOwM0PpkUekI";


    //Construct url
    private String createUrl(LatLng origin, LatLng dest){

        //TODO: INCLUDE REGION IN URL CONSTRUCTION
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

    public RoutesModel GetDirections(LatLng origin, LatLng dest){

        String response = getResponse(createUrl(origin,dest));
        RoutesModel route = new RoutesModel();
        //TODO: Break down the JSON response and return it to where it was called

        try {
            JSONObject root = new JSONObject(response);

            //Set the route legs
            route.setRouteLegs(getRouteLegs(root));
            return route;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Extract the route bounds from the json string
    //TODO: RESEARCH NEEDED HERE
    private RouteBounds getBounds(JSONObject root){

        RouteBounds bounds = new RouteBounds(null);

        try {
            JSONArray boundsArray = root.getJSONArray("bounds");
            for(int i = 0; i < boundsArray.length(); i++){



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bounds;
    }

    //Extract the route legs from the json string
    private List<RouteLeg> getRouteLegs(JSONObject root){

        List<RouteLeg> routeLegs = new ArrayList<RouteLeg>();

        try {
            JSONArray legsArray = root.getJSONArray("legs");

            for(int i = 0; i < legsArray.length(); i++){
                RouteLeg leg = new RouteLeg();
                JSONObject jsonObject = legsArray.getJSONObject(i);
                //Get Distance
                leg.setDistance(jsonObject.getJSONObject("distance").getDouble("value"));

                //Get duration
                leg.setDuration(jsonObject.getJSONObject("duration").getDouble("value"));

                //Get end location
                leg.setEndAddress(jsonObject.getString("end_address"));

                //Get start address
                leg.setStartAddress(jsonObject.getString("start_location"));

                //Get end location
                //TODO: UPDATE HELPER METHOD TO ACCEPT END LOCATION COORDINATES

                //Get start location
                //TODO: UPDATE HELPER METHOD TO ACCEPT START LOCATION COORDINATES

                //Get the route steps
                leg.setSteps(getSteps(jsonObject.getJSONArray("steps")));

                //Get traffic speed info


                //Get via waypoint data

                routeLegs.add(leg);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return routeLegs;
    }

    //Method to extract steps from route legs
    private List<RouteStep> getSteps(JSONArray jsonArray){

        List<RouteStep> routeSteps = new ArrayList<RouteStep>();

        //TODO: EXTRACT THE STEPS

        return routeSteps;
    }
}
