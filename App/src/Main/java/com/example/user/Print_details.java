package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.Common.Donatefood;
import com.example.loginregister.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Print_details extends AppCompatActivity {
    TextView name_,fooditem_,quantity_,address_,date_;
    Button button1;
    public String str1,str2,str3,str4,str5,str6,str7,str8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_details);
        name_=(TextView)findViewById(R.id.name_);
        fooditem_=(TextView)findViewById(R.id.fooditem_);
        quantity_=(TextView)findViewById(R.id.quantity_);
        address_=(TextView)findViewById(R.id.address_);
        date_=(TextView)findViewById(R.id.date_);

        Intent intent=getIntent();
       str1=intent.getStringExtra("name2");
      str2=intent.getStringExtra("fooditem2");
       str3=intent.getStringExtra("quantity2");
      str4=intent.getStringExtra("foodtiming2");
       str5=intent.getStringExtra("address2");
      str6=intent.getStringExtra("date2");
       str7=intent.getStringExtra("time2");

        //String str=str1+" "+str2+" "+str3+" "+str4+" "+str5;
        Donatefood fd=new Donatefood();
        name_.setText(str1);
        fooditem_.setText(str2);
        quantity_.setText(str3);
        address_.setText(str5);
         str8=str6+"\n"+"TIME-"+str7;
        date_.setText(str8);

        button1=(Button)findViewById(R.id.bb);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Print_details.this,Thankyou.class);
                startActivity(intent);
            }
        });




    }
}