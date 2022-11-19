package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText email1,password;

    Button loginbtn;
    ProgressBar pbar;
    FirebaseAuth fauth;
    TextView newuser;
    CheckBox remember;
    TextView forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        fauth=FirebaseAuth.getInstance();
        FirebaseUser user=fauth.getCurrentUser();

        final boolean[] val = {false};
        email1=findViewById(R.id.email);
        password=findViewById(R.id.password1);
        loginbtn=findViewById(R.id.Login);
        pbar=findViewById(R.id.progressBar2);
        newuser=findViewById(R.id.textView5);
        remember=findViewById(R.id.remember);
        forgotpass=findViewById(R.id.forgotpass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetmail=new EditText(v.getContext());
                AlertDialog.Builder passwordresetdialog =new AlertDialog.Builder(v.getContext());
                passwordresetdialog.setTitle("Reset Password");
                passwordresetdialog.setMessage("Enter Email");
                passwordresetdialog.setView(resetmail);
                passwordresetdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail=resetmail.getText().toString().trim();
                        fauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Rest mail sent succesfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "unsucessful "+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
                passwordresetdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordresetdialog.create().show();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Login.this, "xx", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Register.class));
                finish();
            }
        });
        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
       String checkbox= preferences.getString("remember","");
       if(checkbox.equals("true"))
       {
         //  Toast.makeText(Login.this, "Login Credentials Unsaved", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(getApplicationContext(),dashboard.class));
            finish();
       }
       else if(checkbox.equals("false"))
       {
           Toast.makeText(Login.this, "Please sign in", Toast.LENGTH_SHORT).show();
       }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Password=password.getText().toString().trim();
                final String Email=email1.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    email1.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }
                if (Password.length()<8){
                    password.setError("Password must be atleast 8 characters");
                    return;
                }
                pbar.setVisibility(View.VISIBLE);




                fauth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            SplashScreen.val=true;
                            pbar.setVisibility(View.INVISIBLE);

                            val[0] =true;
                            FirebaseUser user=fauth.getCurrentUser();
                            if(!user.isEmailVerified()){
                                Toast.makeText(Login.this, "user not verified", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), dashboard.class));
                            finish();

                        }
                        else{
                            Toast.makeText(Login.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pbar.setVisibility(View.INVISIBLE);


                        }
                    }
                });

            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked() )
                {
                    SharedPreferences sp=getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor Ed=sp.edit();
                    Ed.putString("remember","true");
                    Ed.apply();
                   // Toast.makeText(Login.this, "Login Credentials saved", Toast.LENGTH_SHORT).show();

                }
                else {
                    SharedPreferences sp=getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor Ed=sp.edit();
                    Ed.putString("remember","false");
                    Ed.apply();
                   // Toast.makeText(Login.this, "Login Credentials Unsaved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}