package com.example.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginregister.R;
import com.example.user.MoneyUpi;

public class Donate_money extends AppCompatActivity {
    Button btnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_money);
        btnn=(Button)findViewById(R.id.button4);

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Donate_money.this, MoneyUpi.class);
                startActivity(intent);
                finish();
            }
        });
    }
}