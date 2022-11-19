package com.example.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.loginregister.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class profile extends AppCompatActivity {

    TextView name, email, phone, don;
    FirebaseAuth fauth;
    FirebaseFirestore firebaseFirestore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_profile);
        name=findViewById(R.id.user_name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        don=findViewById(R.id.donations);

        fauth=FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        userId = fauth.getCurrentUser().getUid();
        DocumentReference documentReference = firebaseFirestore.collection("user").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("name"));
                email.setText(value.getString("email"));
                phone.setText(value.getString("phone"));
            }
        });
        DocumentReference documentReference2 = firebaseFirestore.collection("don_details").document(userId);

        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                don.setText(value.getString("no_of_don"));
            }
        });


    }
}