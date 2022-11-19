package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText name,email,pass,phone;
    Button register;
    TextView loginbtn;
    FirebaseAuth fauth;
    ProgressBar pbar;
    FirebaseFirestore fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_register);
        name=findViewById(R.id.FullName);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password1);
        phone=findViewById(R.id.Phone);
        register=findViewById(R.id.button);
        loginbtn=findViewById(R.id.textView3);
       pbar=findViewById(R.id.progressBar);
        fauth=FirebaseAuth.getInstance();
        fb=FirebaseFirestore.getInstance();
        if(fauth.getCurrentUser()!=null){
            //startActivity(new Intent(getApplicationContext(),Login.class));
            //finish();
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Password=pass.getText().toString().trim();
                final String Email=email.getText().toString().trim();
                final String Phone=phone.getText().toString().trim();
                final String  fullname=name.getText().toString();
                if (TextUtils.isEmpty(Email)){
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    pass.setError("Password is required");
                    return;
                }
                if (Password.length()<8){
                    pass.setError("Password must be atleast 8 characters");
                    return;
                }
                if(Phone.length()!=10)
                {
                    phone.setError("Enter valid phone number");
                    return;
                }
                if (TextUtils.isEmpty(fullname)){
                    name.setError("fullname is required");
                    return;
                }
               pbar.setVisibility(View.VISIBLE);
                fauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "Created Succesfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser fuser=fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Verification has been sent", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "email not sent"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                           String userId=fauth.getCurrentUser().getUid();
                            DocumentReference documentReference2=fb.collection("user").document(userId);

                            Map<String,Object> user=new HashMap<>();
                            user.put("name",fullname);
                           user.put("email",Email);
                            user.put("phone",Phone);
                            user.put("no_of_don","0");
                            documentReference2.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   // Toast.makeText(Register.this, "added", Toast.LENGTH_SHORT).show();
                                  //  startActivity( new Intent(getApplicationContext(), Login.class));
                                   // finish();

                                }
                            });
                            DocumentReference documentReference3=fb.collection("don_details").document(userId);
                            Map<String,Object> user1=new HashMap<>();

                            user1.put("no_of_don","0");
                            documentReference3.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   // Toast.makeText(Register.this, "added", Toast.LENGTH_SHORT).show();
                                    //startActivity( new Intent(getApplicationContext(), Login.class));
                                    //finish();

                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            finish();

                            SplashScreen.val=true;
                        }
                        else{
                           pbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}