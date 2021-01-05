package com.example.koffeekafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import javax.annotation.Nullable;

public class UserProfile extends AppCompatActivity {

    ImageButton listButton ,homeButton , loginButton ;
    ImageView locIcon;
    TextView name,email;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        homeButton = (ImageButton)findViewById(R.id.home);
        listButton = (ImageButton)findViewById(R.id.list);
        loginButton = (ImageButton)findViewById(R.id.login);
        locIcon = (ImageView)findViewById(R.id.locationIcon);
        name = (TextView)findViewById(R.id.fName);
        email =(TextView)findViewById(R.id.emailAdd);
        fStore = FirebaseFirestore.getInstance();

        //authentication
        fAuth= FirebaseAuth.getInstance();

        //Fetching the user id of the logged in user
        userID=fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);

        //creating snapshot listener
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
            name.setText(documentSnapshot.getString("fName"));
            email.setText(documentSnapshot.getString("email"));
            }
        });

        locIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(UserProfile.this,GoogleMap.class);
                startActivity(intentLoadNewActivity);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(UserProfile.this,MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(UserProfile.this,menu_list.class);
                startActivity(intentLoadNewActivity);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(UserProfile.this, Login.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}