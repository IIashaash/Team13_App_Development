package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user.MoneyUpi;
import com.example.user.dashboard;

public class SplashScreen extends AppCompatActivity {

    protected static boolean val=false;
    private static int TIMER=5000;
    //variables
    TextView logo, quote, name1;
    ImageView background;
    //animations
    Animation side, top, bottom1, bottom2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //     WindowManager.LayoutParams.FLAG_FULLSCREEN  );
        setContentView(R.layout.splash_main);

        logo=findViewById(R.id.textVieww);
        //poweredby=findViewById(R.id.powered_by);

        background=findViewById(R.id.im1);
        quote=findViewById(R.id.textView7);
        name1=findViewById(R.id.textView10);

        side= AnimationUtils.loadAnimation(this,R.anim.side_anim);
        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom1= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        bottom2= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        background.setAnimation(side);
        logo.setAnimation(top);
        quote.setAnimation(bottom1);
        name1.setAnimation(bottom2);
        //poweredby.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(val==false)
                {
                    Intent newActivity=new Intent(getApplicationContext(), Login.class);
                    startActivity(newActivity);
                    finish();
                }
                else
                {
                    Intent newActivity=new Intent(getApplicationContext(), dashboard.class);
                    startActivity(newActivity);
                    finish();
                }

            }
        },TIMER);
    }
}