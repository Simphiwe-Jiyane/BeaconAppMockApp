package com.example.beaconappmockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;
//TODO: Turn into fragment
public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Animation variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView brandName, slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO: Check if user already exists
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = findViewById(R.id.imageView);
        slogan = findViewById(R.id.brand_slogan);
        brandName = findViewById(R.id.brand_name);

        logo.setAnimation(topAnim);
        brandName.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,AccessActivity.class);

                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair(logo, "logo_transition");
                pairs[1] = new Pair(slogan, "slogan_transition");
                pairs[2] = new Pair(brandName, "tagline_transition");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        }, SPLASH_SCREEN);
    }
}