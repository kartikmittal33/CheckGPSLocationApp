package com.checkgpslocationapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GPSTracker gpsTracker;

    boolean pressed = false;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            System.out.println("heyy");
            gpsTracker = new GPSTracker(MainActivity.this);
            float acc = gpsTracker.getAccuracy();
            double lat = gpsTracker.getLatitude();
            double lng = gpsTracker.getLongitude();
            Toast.makeText(getApplicationContext(), "Longitude:" + lng + " Latitude: " + lat + " Accuracy: " + acc, Toast.LENGTH_SHORT).show();
            if(gpsTracker != null)
                gpsTracker.stopUsingGPS();
            handler.postDelayed(this, 6000);
        }
    };

    public void startTrack(View v) {
//        gpsTracker = new GPSTracker(MainActivity.this);

        handler = new Handler();
        pressed = true;
        handler.post(runnable);


    }

    public void stopTrack(View v) {
        if(gpsTracker != null)
            gpsTracker.stopUsingGPS();
        pressed = false;
        handler.removeCallbacks(runnable);
    }
}
