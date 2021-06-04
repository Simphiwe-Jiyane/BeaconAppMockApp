package com.example.beaconappmockapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_search extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PostAdapter adapter;
    List<Posts> postsList = new ArrayList<>();
    Button  btnLandMark;

    //Progressbar variable
    private ProgressBar spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostAdapter(postsList);
        recyclerView.setAdapter(adapter);

        //Initialize ProgressBar
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        //LandMarkBtn
        btnLandMark = findViewById(R.id.btnLandmarks);

        //LandMarkbtn OnClick
        btnLandMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Spinner Visible
                spinner.setVisibility(View.VISIBLE);
                fetchPosts();
                //Spinner Invisible
                spinner.setVisibility(View.GONE);
            }
        });



    }
    //Method that puts data in recycler view
    private void fetchPosts(){
        RetrofitClient.getRetrofitClient().getPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful()&& response.body()!=null){
                    postsList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(activity_search.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}