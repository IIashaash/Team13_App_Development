package com.example.user;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.Common.About;
import com.example.Common.Contact;
import com.example.Common.Donate_money;
import com.example.Common.Donatefood;
import com.example.HelperClass.HomeAdapter.FeaturedAdapter;
import com.example.HelperClass.HomeAdapter.FeaturedAdapter2;
import com.example.HelperClass.HomeAdapter.FeaturedHelperClass;
import com.example.HelperClass.HomeAdapter.FeaturedHelperClass2;
import com.example.loginregister.Login;
import com.example.loginregister.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    //variables
    static final float ENDSCALE=0.7f;
    LinearLayout contentView;

    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView nav_view;
    //menu button
    ImageView menu_img, plus_btn;
    //Recycler view
    RecyclerView featuredRecycler;
    RecyclerView featuredRecycler2;
    RecyclerView.Adapter adapter,adapter2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN  );
        setContentView(R.layout.activity_dashboard);


        //hooks
        featuredRecycler=findViewById(R.id.featured_cycle);
       featuredRecycler2=findViewById(R.id.featured_recycle2);
        featuredcycler();
        featuredcycler2();


        contentView=findViewById(R.id.content);

        //menu hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        nav_view=findViewById(R.id.nav_view);
        //select home
        nav_view.setCheckedItem(R.id.Home);
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

    private void featuredcycler2() {
        featuredRecycler2.setHasFixedSize(true);
        featuredRecycler2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<FeaturedHelperClass2> featuredlocations = new ArrayList<>();
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.syria,"WFP also provides food assistance to Syrian refugees who have fled to Iraq."));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.covid_usa,"Restuarants donating food during covid 19 in United States of America"));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.f1,"Over 820 million people suffering from hunger; \"Global Challenge\""));

        featuredlocations.add(new FeaturedHelperClass2(R.drawable.congo,"Food donation camp conducted by the Congo Government to feed the needy"));
   /*     featuredlocations.add(new FeaturedHelperClass2(R.drawable.g8,""));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.g4,""));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.g5,""));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.g6,""));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.g7,""));
        featuredlocations.add(new FeaturedHelperClass2(R.drawable.g8,""));*/

        adapter2 = new FeaturedAdapter2(featuredlocations);
        featuredRecycler2.setAdapter(adapter2);
    }

    //recycler fucctioncs
    private void featuredcycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<FeaturedHelperClass> featuredlocations = new ArrayList<>();
        featuredlocations.add(new FeaturedHelperClass(R.drawable.g1));
       // featuredlocations.add(new FeaturedHelperClass(R.drawable.g7));
        //featuredlocations.add(new FeaturedHelperClass(R.drawable.g8));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.g4));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.g5));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.g6));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.g7));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.g8));

        adapter = new FeaturedAdapter(featuredlocations);
        featuredRecycler.setAdapter(adapter);

    }


    //navigation functions
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

    public void animate_nav_bar(){

        drawerLayout.setScrimColor(getResources().getColor(R.color.lightwhite));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //scale the view based on current slide offset
                final float diffScaledOffSet=slideOffset*(1-ENDSCALE);
                final float offsetScale=1-diffScaledOffSet;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //translate the view accounting for the scaled width
                final float xoffSet=drawerView.getWidth()*slideOffset;
                final float xoffSetdiff = contentView.getWidth()*diffScaledOffSet/2;
                final float xTranslation = xoffSet-xoffSetdiff;
                contentView.setTranslationX(xTranslation);



            }


        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.donatefood:
                startActivity(new Intent(getApplicationContext(), Donatefood.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(getApplicationContext(), About.class));
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