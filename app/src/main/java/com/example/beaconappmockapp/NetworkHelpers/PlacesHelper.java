package com.example.beaconappmockapp.NetworkHelpers;

import android.content.res.Resources;

import com.example.beaconappmockapp.GooglePlacesHelpers.Places.GooglePlaceHelper;
import com.example.beaconappmockapp.GooglePlacesHelpers.Places.PlaceGeometry;
import com.example.beaconappmockapp.GooglePlacesHelpers.Places.PlaceViewport;
import com.example.beaconappmockapp.GooglePlacesHelpers.Places.PlacesLocation;
import com.example.beaconappmockapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlacesHelper {

    public String GetPlacesResponse(String url){
        NetworkUtil helper = new NetworkUtil(url);
        String response = helper.GetJsonResponse();
        return response;
    }

    //Construct the url
    private String createUrl(String search){
        search.toLowerCase();
        String url = "";
        String params = "";
        String args[] = search.split(" ");

        for (int i = 0; i < args.length; i++){
            if(i == args.length - 1){
                params += args[i];
            }
            else{
                params += args[i]+"%20";
            }

        }

        url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+params+"&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyBSXbvQ6E_PPDHtjtiIke1fOwM0PpkUekI";
        return url;
    }


    //Get a list of all the possible locations from the search
    public List<GooglePlaceHelper> getPlace(String search){

        String response = GetPlacesResponse(createUrl(search));

        List<GooglePlaceHelper> list = new ArrayList<GooglePlaceHelper>();

        GooglePlaceHelper helper = new GooglePlaceHelper();

        //Set the name and address of the Places object
        try {
            JSONObject root = new JSONObject(response);
            JSONArray jsonArray = root.getJSONArray("candidates");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                helper.setName(jsonObject.getString("name"));
                helper.setAddress(jsonObject.getString("formatted_address"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Attempt to process get the (Location) and (Viewport) latitude and longitude from the geometry object
        try{
            JSONObject root = new JSONObject(response);
            JSONArray jsonArray = root.getJSONArray("candidates");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject geometry = jsonObject.getJSONObject("geometry");
                //Set the geometry of the Places object
                helper.setGeometry( new PlaceGeometry(GetPlacesLocation(geometry), getPlaceViewport(geometry)));
                //Add the helper to the list and return
                list.add(helper);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


    //Get the latitude and longitude from the location object using the passed geometry object
    private PlacesLocation GetPlacesLocation(JSONObject geometry){

        PlacesLocation location = null;
        try {

            location = new PlacesLocation(geometry.getJSONObject("location").getDouble("lat")
                    ,geometry.getJSONObject("location").getDouble("lng"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }

    //Get the latitude and longitude from the viewport object using the passed geometry object
    private PlaceViewport getPlaceViewport(JSONObject geometry){

        PlaceViewport viewport = null;

        try {
            JSONObject view = geometry.getJSONObject("viewport");
            JSONObject northeast = view.getJSONObject("northeast");
            JSONObject southwest = view.getJSONObject("southwest");
            viewport = new PlaceViewport(northeast.getDouble("lat")
                        , northeast.getDouble("lng")
                        ,southwest.getDouble("lat")
                        ,southwest.getDouble("lng"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return viewport;
    }
}
