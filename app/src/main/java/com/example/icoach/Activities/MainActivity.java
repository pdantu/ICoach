package com.example.icoach.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.location.LocationListener;
import android.location.LocationManager;

import com.example.icoach.Models.Data;
import com.example.icoach.R;
import com.firebase.client.Firebase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    Firebase db;
    Button imageProcessed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageProcessed = findViewById(R.id.imageProcessed);
        Firebase.setAndroidContext(this);
        db = new Firebase("https://icoach-68bf4.firebaseio.com/");

        //Arraylist of coordinates for initial location when app is launched
        ArrayList<Double> initCord = getLoc();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Set the appropriate lat/longitutde for the inital location
        final Location initLoc;
        initLoc = new Location("Initial");
        initLoc.setLatitude(initCord.get(0));
        initLoc.setLongitude(initCord.get(1));
        //Store the current time in seconds
        final double initTime = System.currentTimeMillis() * 1.0;

        //TODO: Implementation of Image processing, For now I included a button that mimicks image being processed
        imageProcessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<Double> onProcessedCord;
                final Location processedLoc;
                processedLoc = new Location("Processed Location");
                onProcessedCord = getLoc();
                processedLoc.setLatitude(onProcessedCord.get(0));
                processedLoc.setLongitude(onProcessedCord.get(1));
                double clickTime = System.currentTimeMillis() * 1.0;
                double timeElapsed = (clickTime/1000.0 - initTime/1000.0);
                double distance = initLoc.distanceTo(processedLoc);
                //TODO: What is heading, left, and right for data ?
                writeData(timeElapsed,distance/timeElapsed, 1,1,1 );
            }
        });


    }

    private void writeData(double seconds, double avgSpeed, int heading, int left, int right) {
        //seconds represents amount of time after race begins
        //characterize data based on time since the race began
        Data d = new Data(seconds,avgSpeed,heading,left,right);
        db.child("Tracking").child(String.valueOf(seconds)).setValue(d);

    }

    //returns an arraylist containing latitude in index 0 and longitutde in index 1
    public ArrayList<Double> getLoc()
    {
        ArrayList<Double> coord = new ArrayList<Double>();
        double lat;
        double lon;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
           && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            Location GPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(GPS != null)
            {
                 lat = GPS.getLatitude();
                 coord.add(lat);
                 lon = GPS.getLongitude();
                 coord.add(lon);
            }
        }
        return coord;
    }


}