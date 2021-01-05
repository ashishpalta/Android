package com.example.koffeekafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    // DECLARATIONS
    ImageButton listButton ,homeButton , reviewsButton, loginButton;
    EditText mfullName,mEmail,mPassword,mreEnterPass;
    Button mRegisterBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView signIn;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullName =(EditText)findViewById(R.id.fullName);
        mEmail =(EditText)findViewById(R.id.emailAddress);
        mPassword =(EditText)findViewById(R.id.password);
        mRegisterBtn=(Button)findViewById(R.id.btnRegister);
        signIn = (TextView)findViewById(R.id.txtSignIp);
        homeButton = (ImageButton)findViewById(R.id.home);
        listButton = (ImageButton)findViewById(R.id.list);
        loginButton = (ImageButton)findViewById(R.id.login);
        mreEnterPass =(EditText)findViewById(R.id.reenter_password);

        //firebase authentication
        fAuth=FirebaseAuth.getInstance();
        //firebase database
        fStore =FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //CHECKING IF THERE IN NO LOGGED IN USER
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        // REGISTER BUTTON FUNCTION
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullName = mfullName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String rePass= mreEnterPass.getText().toString().trim();

                //Validating the input data in the field
                if(TextUtils.isEmpty(fullName)){
                    mfullName.setError("Can't leave the field empty");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Password Must be >= 6");
                    return;
                }
                if(TextUtils.isEmpty(rePass)){
                    mreEnterPass.setError("Please reEnter Your Password");
                    return;
                }
                if(!password.equals(rePass)){
                    mreEnterPass.setError("Password didn't matched. Retry");
                    return;
                }
                //Setting progressbar to be visible whenever login button is pressed
                progressBar.setVisibility(View.VISIBLE);

                //CONNECTING TO FIREBASE AND REGISTERING AND AUTHENTICATING USER WITH IT
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created ",Toast.LENGTH_SHORT).show();
                            //creating a document in database and pushing user inputs
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                Log.d("TAG","onSuccess: user profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","onFailure:"+ e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        //BACK TO SIGNiN PAGE
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Register.this,Login.class);
                startActivity(intentLoadNewActivity);
            }
        });
        //HOMEPAGE LISTENER
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Register.this,MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        //MENU LIST LISTENER
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Register.this,menu_list.class);
                startActivity(intentLoadNewActivity);
            }
        });

        //LOGIN PAGE
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Register.this,Login.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}