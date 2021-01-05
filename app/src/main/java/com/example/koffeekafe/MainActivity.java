package com.example.koffeekafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.koffeekafe.Login;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImagesUrl = new ArrayList<>();
    ImageButton listButton ,homeButton , reviewsButton, loginButton;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        homeButton = (ImageButton)findViewById(R.id.home);
        listButton = (ImageButton)findViewById(R.id.list);
        loginButton = (ImageButton)findViewById(R.id.login);



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this,menu_list.class);
                startActivity(intentLoadNewActivity);
            }
        });

//        if (fAuth.getCurrentUser() != null) {
//            loginButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intentLoadNewActivity = new Intent(MainActivity.this, Register.class);
//                    startActivity(intentLoadNewActivity);
//                }
//            });
//        }
//        else {
//            loginButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intentLoadNewActivity = new Intent(MainActivity.this, Register.class);
//                    startActivity(intentLoadNewActivity);
//                }
//            });
//        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this,Login.class);
                startActivity(intentLoadNewActivity);
            }
        });


        getImages();

    }



    private void getImages(){


        mImagesUrl.add("https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=762&q=80");
        mNames.add("Moca Coffee");

        mImagesUrl.add("https://images.unsplash.com/photo-1458819714733-e5ab3d536722?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=333&q=80");
        mNames.add("Magretta");

        mImagesUrl.add("https://images.unsplash.com/photo-1530032582480-edd739014c39?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=371&q=80");
        mNames.add("Hot Hundai");

        mImagesUrl.add("https://images.unsplash.com/photo-1502849394214-c4b6352145ee?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=362&q=80");
        mNames.add("Mood Charger");


        mImagesUrl.add("https://images.unsplash.com/photo-1461023058943-07fcbe16d735?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80");
        mNames.add("Montana Special");

        mImagesUrl.add("https://images.unsplash.com/photo-1512151004335-d5b6c2ff7e12?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80");
        mNames.add("Special");


        mImagesUrl.add("https://images.unsplash.com/photo-1519333796630-4b7d19ad1bb3?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80");
        mNames.add("KK Hot Spec");

        mImagesUrl.add("https://images.unsplash.com/photo-1484251065541-c9770829890f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80");
        mNames.add("Antralia");

        mImagesUrl.add("https://www.thespruceeats.com/thmb/FnYDe_oiInGLwlEy8FNQocRTQb0=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/cup-of-coffee-on-a-table-of-wood-595258391-5893e9dd5f9b5874ee534e48.jpg");
        mNames.add("Avzi");

        initRecyclerView();

    }

    private void initRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImagesUrl);
        recyclerView.setAdapter(adapter);
    }




}