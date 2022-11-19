package com.example.Common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.loginregister.Login;
import com.example.loginregister.R;
import com.example.loginregister.Register;
import com.example.user.Print_details;
import com.example.user.dashboard;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Donatefood extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    //TextInputLayout textInputLayout;
    LocationManager locationManager;
    LocationListener locationListener;
    Button button,button1;
 public   TextInputEditText name1,fooditem1,quantity1,foodtiming1,address1;
public    TextInputEditText date,time1;
    TimePickerDialog picker;
    String currentAdress,x2;
    FirebaseAuth fauth;
    FirebaseFirestore fb;
    String user_n,user_e,user_p;

 public   String n,time, ft, ftiming, qt, ad,d;
 String userId;
 Integer x1=new Integer(0);
   TextView t1;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donatefood);
        fauth=FirebaseAuth.getInstance();
        fb=FirebaseFirestore.getInstance();
       // fb1=FirebaseFirestore.getInstance();
         userId = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        fb.collection("don_details").document(userId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.i("rohit", "Listen failed.", e);
                         //   Toast.makeText(getApplicationContext(),"Listen Failed",Toast.LENGTH_LONG).show();

                            return;
                        }
                        if (snapshot != null && snapshot.exists()) {
                            Object players =snapshot.getData().get("no_of_don");

                            x1=Integer.parseInt(players.toString())+1;
                            Object p2=snapshot.getData().get("name");

                            Log.i("value", ""+players.toString(), e);
                          //  Toast.makeText(getApplicationContext(),players.toString()+" sub 1 "+x1,Toast.LENGTH_LONG).show();

                        } else {
                            Log.i("", "Current data: null");
                          //  Toast.makeText(getApplicationContext(),"current data null",Toast.LENGTH_LONG).show();
                        }
                    }
                });


         locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Log.i("Location",location.toString());
                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> listadresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    if (listadresses!=null && listadresses.size()>0){
                        //Log.i("onchanged",listadresses.get(0).toString());
                        String adress="";
                        if (null != listadresses.get(0).getThoroughfare())
                            adress += listadresses.get(0).getThoroughfare()+" ";
                        if (null != listadresses.get(0).getAdminArea())
                            adress += listadresses.get(0).getAdminArea()+" ";
                        if (null != listadresses.get(0).getLocality())
                            adress += listadresses.get(0).getLocality()+" ";
                        if (null != listadresses.get(0).getPostalCode())
                            adress += listadresses.get(0).getPostalCode()+" ";
                        currentAdress=adress;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }
        //textInputLayout = findViewById(R.id.fooditem);
        name1=findViewById(R.id.name1);
        fooditem1=findViewById(R.id.fooditem1);
        quantity1=findViewById(R.id.quantity1);
        foodtiming1=findViewById(R.id.foodtiming1);
        address1=findViewById(R.id.address1);
        time1=findViewById(R.id.time1);
        button1=findViewById(R.id.gps1);

        button = (Button) findViewById(R.id.submit);

        date = findViewById(R.id.date1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address1.setText(currentAdress);;
        //        button1.setVisibility(View.VISIBLE);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Print_details pd=new Print_details();
                 n=name1.getText().toString().trim();

                ft=fooditem1.getText().toString().trim();
               qt=quantity1.getText().toString().trim();
               ad=address1.getText().toString().trim();
                 ftiming=foodtiming1.getText().toString().trim();
              time=time1.getText().toString().trim();
                d=date.getText().toString().trim();

                if (TextUtils.isEmpty(n)){
                    name1.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(ft)){
                    fooditem1.setError("foodItem is required");
                    return;
                }
                if (TextUtils.isEmpty(qt)){
                    quantity1.setError("This field is required");
                    return;
                }if (TextUtils.isEmpty(time)){
                    time1.setError("This field is required");
                    return;
                }if (TextUtils.isEmpty(ftiming)){
                    foodtiming1.setError("This field is required");
                    return;
                }
                if (TextUtils.isEmpty(ad)){
                    address1.setError("This field is required");
                    return;
                }
                if (TextUtils.isEmpty(d)){
                    date.setError("This field is required");
                    return;
                }



            //    userId=userId+x1.toString();

             //   Toast.makeText(getApplicationContext(),"  sub2 :"+x1,Toast.LENGTH_LONG).show();
                int x3=x1;
                x2=userId+String.valueOf(x3);
           //     Toast.makeText(getApplicationContext(),"  sub3 "+x2,Toast.LENGTH_LONG).show();
                DocumentReference documentReference=fb.collection("donars").document(x2);
              DocumentReference documentReference2=fb.collection("don_details").document(userId);

                Map<String,Object> user=new HashMap<>();
                user.put("no_of_don",String.valueOf(x1));
               user.put("name",user_n);
                user.put("email",user_e);
                user.put("phone",user_p);
                documentReference2.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                     //   Toast.makeText(Donatefood.this, "added", Toast.LENGTH_SHORT).show();

                    }
                });

                String s;


                //   Task<DocumentSnapshot> ss=documentReference.get();
             //   DocumentSnapshot gh=ss.getResult();


                Map<String,Object> donar=new HashMap<>();
                donar.put("fname",n);
                donar.put("quantity",qt);
                donar.put("Fooditem",ft);
                donar.put("adress",ad);
                donar.put("foodTime",ftiming);
                donar.put("time",time);
                donar.put("date",d);
                //Toast.makeText(Donatefood.this, "Half a way", Toast.LENGTH_SHORT).show();



                //startActivity( new Intent(getApplicationContext(), Print_details.class));
                //finish();
                documentReference.set(donar).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                     //   Toast.makeText(Donatefood.this, "added", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Donatefood.this,Print_details.class);
                        intent.putExtra("name2",n);
                        intent.putExtra("fooditem2",ft);
                        intent.putExtra("quantity2",qt);
                        intent.putExtra("foodtiming2",ftiming);
                        intent.putExtra("address2",ad);
                        intent.putExtra("date2",d);
                        intent.putExtra("time2",time);

                        startActivity(intent);
                        finish();

                    }
                });








            }
        });


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
                picker = new TimePickerDialog(Donatefood.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time1.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });






       /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //String hi=test+test1+test2+test3+test4;





                //Toast.makeText(getApplicationContext(),hi,Toast.LENGTH_LONG).show();
            }

        });*/
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

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(), dashboard.class));
        finish();
        super.onBackPressed();
    }
}