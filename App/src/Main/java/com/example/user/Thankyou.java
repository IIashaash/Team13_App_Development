package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.loginregister.R;

public class Thankyou extends AppCompatActivity {
    private static final long SPLASH_SCREEN = 5000 ;
    Animation top_anim,bottom_anim;
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);


        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        image1=findViewById(R.id.im1);
        //logo=findViewById(R.id.textView);

        image1.setAnimation(top_anim);
        //logo.setAnimation(bottom_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Thankyou.this, dashboard.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}