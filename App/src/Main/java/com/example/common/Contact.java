package com.example.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.loginregister.Login;
import com.example.loginregister.R;
import com.example.user.dashboard;
import com.example.user.profile;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Contact extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //variables
    static final float ENDSCALE=0.7f;
    LinearLayout contentView;

    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView nav_view;
    //menu button
    ImageView menu_img, plus_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN  );

        setContentView(R.layout.activity_contact);



        contentView=findViewById(R.id.content);

        //menu hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        nav_view=findViewById(R.id.nav_view);
        //select home
        nav_view.setCheckedItem(R.id.contact_us);
        //menu icon
        menu_img = findViewById(R.id.menu_img);

        navigation_view();
        plus_btn=findViewById(R.id.plus_button);
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Donatefood.class));
                finish();
            }
        });

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
            case R.id.donatefood:
                startActivity(new Intent(getApplicationContext(), Donatefood.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(getApplicationContext(), About.class));
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
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), profile.class));
                break;
            case R.id.donatemoney:
                startActivity(new Intent(getApplicationContext(), Donate_money.class));
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