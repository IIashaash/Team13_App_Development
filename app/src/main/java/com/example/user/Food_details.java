package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import com.example.loginregister.R;

public class Food_details extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button button,button1;
    TextInputEditText name1,fooditem1,quantity1,foodtiming1,address1;
    TextInputEditText date,time1;
    TimePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        name1=findViewById(R.id.name1);
        fooditem1=findViewById(R.id.fooditem1);
        quantity1=findViewById(R.id.quantity1);
        foodtiming1=findViewById(R.id.foodtiming1);
        address1=findViewById(R.id.address1);
        time1=findViewById(R.id.time1);
        button1=findViewById(R.id.gps1);

        button = (Button) findViewById(R.id.submit);

        date = findViewById(R.id.date1);

        findViewById(R.id.date1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                //Toast.makeText(getApplicationContext(),hi,Toast.LENGTH_LONG).show();
            }

        });
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(Food_details.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time1.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String test=name1.getText().toString().trim();
                String test1=fooditem1.getText().toString().trim();
                String test2=quantity1.getText().toString().trim();
                String test3=foodtiming1.getText().toString().trim();
                String test4=address1.getText().toString().trim();
                String test5=date.getText().toString().trim();
                String test6=time1.getText().toString().trim();

                //String hi=test+test1+test2+test3+test4;
                Intent intent=new Intent(Food_details.this, Print_details.class);
                intent.putExtra("name2",test);
                intent.putExtra("fooditem2",test1);
                intent.putExtra("quantity2",test2);
                intent.putExtra("foodtiming2",test3);
                intent.putExtra("address2",test4);
                intent.putExtra("date2",test5);
                intent.putExtra("time2",test6);

                startActivity(intent);





                //Toast.makeText(getApplicationContext(),hi,Toast.LENGTH_LONG).show();
            }

        });
    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String datee = dayOfMonth + "-" +( month+1) + "-" + year;
        date.setText(datee);
    }




    }
