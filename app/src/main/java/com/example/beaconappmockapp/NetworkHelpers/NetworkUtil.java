package com.example.beaconappmockapp.NetworkHelpers;

import android.os.AsyncTask;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtil extends AsyncTask<Request,String,String>{

    private String url;
    private String jsonResponse;
    private OkHttpClient client = new OkHttpClient();

    //Constructor
    public NetworkUtil(String urlPassed){

        this.url = urlPassed;

        getRequest();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    //Generate request and get response
    private String getRequest(){
        //Build request url
        Request[] requests = {new Request.Builder().url(this.url).build()};
        String[] myResponse = new String[1];
        client.newCall(requests[0]).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                GetJsonResponse(response.body().string());
            }
        });

        this.jsonResponse = myResponse[0];
        return myResponse[0];
    }


    public String GetJsonResponse(){
        return this.jsonResponse;
    }

    private void GetJsonResponse(String res){
        this.jsonResponse = res;
    }



    @Override
    protected String doInBackground(Request... requests) {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Simz";
    }
}
