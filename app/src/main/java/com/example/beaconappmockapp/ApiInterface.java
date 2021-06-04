package com.example.beaconappmockapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/places")// the api url library name
    Call<List<Posts>> getPosts();
}
