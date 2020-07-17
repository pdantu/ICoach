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
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    Button imageProcessed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageProcessed = findViewById(R.id.imageProcessed);
        Firebase.setAndroidContext(this);
        db = FirebaseFirestore.getInstance(FirebaseApp.initializeApp(this));
        final double initTime = System.currentTimeMillis() * 1.0;
        //TODO: Implementation of Image processing, For now I included a button that mimicks image being processed
        imageProcessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double clickTime = System.currentTimeMillis() * 1.0;
                double timeElapsed = (clickTime/1000.0 - initTime/1000.0);
                //TODO: What is heading, left, and right for data ?
                writeData(12,9.0, 40,90,80);
            }
        });
    }

    private void writeData(int timeElapsed, double avgSpeed, int heading, int left, int right) {
        Data d = new Data(timeElapsed,avgSpeed,heading,left,right);
        db.collection("test").add(d);
    }
}
