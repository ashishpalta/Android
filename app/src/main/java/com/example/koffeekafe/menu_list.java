package com.example.koffeekafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.koffeekafe.Login;

public class menu_list extends AppCompatActivity {
    ImageButton listButton ,homeButton ,  loginButton;
    CardView cardView,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);


        homeButton = (ImageButton)findViewById(R.id.home);
        listButton = (ImageButton)findViewById(R.id.list);
        loginButton = (ImageButton)findViewById(R.id.login);
        cardView = (CardView) findViewById(R.id.cv1);
        cardView2 = (CardView) findViewById(R.id.cv2);
        cardView3 = (CardView) findViewById(R.id.cv3);
        cardView4 = (CardView) findViewById(R.id.cv4);
        cardView5 = (CardView) findViewById(R.id.cv5);
        cardView6= (CardView) findViewById(R.id.cv6);
        cardView7 = (CardView) findViewById(R.id.cv7);
        cardView8 = (CardView) findViewById(R.id.cv8);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,ScrollingActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this,menu_list.class);
                startActivity(intentLoadNewActivity);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(menu_list.this, Login.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}