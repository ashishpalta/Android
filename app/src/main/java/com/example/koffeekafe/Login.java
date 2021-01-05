package com.example.koffeekafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    //Declaring Variables
    Button registerButton , mLogin;
    EditText mEmail,mPassword;
    ImageButton listButton ,homeButton , reviewsButton, loginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerButton =(Button)findViewById(R.id.btnRegister);
        homeButton = (ImageButton)findViewById(R.id.home);
        listButton = (ImageButton)findViewById(R.id.list);
        loginButton = (ImageButton)findViewById(R.id.login);
        mLogin =(Button)findViewById(R.id.btnLogin) ;
        mEmail =(EditText)findViewById(R.id.username);
        mPassword = (EditText)findViewById(R.id.password);
        fAuth= FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.progressBar);
        forgetPassword =(TextView)findViewById(R.id.forgotPass);

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),UserProfile.class));
            finish();
        }

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your email to receive Reset link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //extract email and sent reset link

                        String mail = resetMail.getText().toString().trim();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link sent to your mail",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error, Reset Link is Not sent " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Close the Dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });

        //SignIn button listener and validation of user credentials
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

            //Validations
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
                progressBar.setVisibility(View.VISIBLE);
            //checking teh user credentials through firebase data
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        //Register activity
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Login.this,Register.class);
                startActivity(intentLoadNewActivity);
            }
        });
        //mainActivity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Login.this,MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        //MenuList
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Login.this,menu_list.class);
                startActivity(intentLoadNewActivity);
            }
        });

        //Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Login.this,Login.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}