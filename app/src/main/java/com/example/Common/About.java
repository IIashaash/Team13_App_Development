package com.example.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.provider.CalendarContract;
import android.sax.Element;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.loginregister.Login;
import com.example.loginregister.R;
import com.example.user.dashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //variables
    static final float ENDSCALE=0.7f;
    LinearLayout contentView;

    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView nav_view;
    //menu button
    ImageView menu_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN  );

        setContentView(R.layout.activity_about);



        contentView=findViewById(R.id.content);

        //menu hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        nav_view=findViewById(R.id.nav_view);
        //select home
        nav_view.setCheckedItem(R.id.about_us);
        //menu icon
        menu_img = findViewById(R.id.menu_img);

        navigation_view();



        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Donate Now is an app designed to help the needy by collecting the excess food from houesholds and various functions and redistributing it to the people in need of it. ")


                .addGroup("CONNECT WITH US!")
                .addEmail("varunsimha@gmail.com")
                .addWebsite("Your website/")
                .addYoutube("UCbekhhidkzkGryM7mi5Ys_w")   //Enter your youtube link here (replace with my channel link)
                .addPlayStore("com.example.yourprojectname")   //Replace all this with your package name
                .addInstagram("@actorprabhas")    //Your instagram id

                .create();
        setContentView(aboutPage);
    }




    private void navigation_view() {
        nav_view.bringToFront();
        nav_view.setNavigationItemSelectedListener(this);

        menu_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);

            }
        });


        //  animate_nav_bar();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.Home:
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                break;
            case R.id.contact_us:
                startActivity(new Intent(getApplicationContext(), Contact.class));
                break;
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();

                SharedPreferences sp=getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor Ed=sp.edit();
                Ed.putString("remember","false");
                Ed.apply();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}