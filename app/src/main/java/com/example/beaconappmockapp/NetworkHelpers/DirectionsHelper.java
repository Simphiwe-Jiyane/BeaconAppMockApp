package com.example.beaconappmockapp.NetworkHelpers;

//Create directions variables


import com.example.beaconappmockapp.GoogleDirectionsHelpers.RouteBounds;
import com.example.beaconappmockapp.GoogleDirectionsHelpers.RouteLeg;
import com.example.beaconappmockapp.GoogleDirectionsHelpers.RouteStep;
import com.example.beaconappmockapp.GoogleDirectionsHelpers.RoutesModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

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
            route.setRouteLegs(getRouteLegs(root.getJSONArray("routes")));


        } catch (JSONException e) {
            e.printStackTrace();

        }
        return route;
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
    private List<RouteLeg> getRouteLegs(JSONArray root){

        List<RouteLeg> routeLegs = new ArrayList<RouteLeg>();

        try {
            for(int x = 0; x < root.length(); x++){

                JSONObject routeObject = root.getJSONObject(x);
                JSONArray legsArray = routeObject.getJSONArray("legs");
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

                    //Get start location
                    leg.setStartLocation(new LatLng(jsonObject.getJSONObject("start_location").getDouble("lat")
                            ,jsonObject.getJSONObject("start_location").getDouble("lng")));

                    //Get end location
                    leg.setEndLocation(new LatLng(jsonObject.getJSONObject("end_location").getDouble("lat")
                            ,jsonObject.getJSONObject("end_location").getDouble("lng")));

                    //Get the route steps
                    leg.setSteps(getSteps(jsonObject.getJSONArray("steps")));

                    //Get traffic speed info


                    //Get via waypoint data

                    //Get summary

                    routeLegs.add(leg);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return routeLegs;
    }

    //Method to extract steps from route legs
    private List<RouteStep> getSteps(JSONArray jsonArray){

        List<RouteStep> routeSteps = new ArrayList<RouteStep>();

        for(int i = 0; i < jsonArray.length(); i++){

            RouteStep step = new RouteStep();

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //Get the distance
                step.setDistance(jsonObject.getJSONObject("distance").getDouble("value"));

                //Get the duration
                step.setDuration(jsonObject.getJSONObject("duration").getDouble("value"));

                //Get the end location
                step.setEndLocation(new LatLng(jsonObject.getJSONObject("end_location").getDouble("lat")
                        ,jsonObject.getJSONObject("end_location").getDouble("lng")));


                //Get the start location
                step.setStartLocation(new LatLng(jsonObject.getJSONObject("start_location").getDouble("lat")
                        ,jsonObject.getJSONObject("end_location").getDouble("lng")));

                //Get the polyline value
                //Extract value from json string response then convert to latlng value
                List<LatLng> stepPolylines = PolyUtil.decode((jsonObject.getJSONObject("polyline").getString("points")));

                step.setStepPolyline(stepPolylines);

                //Get the maneuver
//                step.setManeuver(jsonObject.getString("maneuver"));

                //Get the travel mode
//                step.setTravelMode(jsonObject.getString("travel_mode"));

                //Add the step to the List<>
                routeSteps.add(step);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return routeSteps;
    }
}
