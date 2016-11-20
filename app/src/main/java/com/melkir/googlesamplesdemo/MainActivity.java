package com.melkir.googlesamplesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.melkir.accelerometerplay.AccelerometerPlayActivity;
import com.melkir.googlesamplesdemo.vision.BarcodeReaderActivity;
import com.melkir.googlesamplesdemo.vision.FaceTrackerActivity;
import com.melkir.googlesamplesdemo.vision.GooglyEyesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startBarcodeReaderActivity(View view) {
        Intent intent = new Intent(this, BarcodeReaderActivity.class);
        startActivity(intent);
    }

    public void startFaceTrackerActivity(View view) {
        Intent intent = new Intent(this, FaceTrackerActivity.class);
        startActivity(intent);
    }

    public void startGooglyEyesActivity(View view) {
        Intent intent = new Intent(this, GooglyEyesActivity.class);
        startActivity(intent);
    }

    public void startAccelerometerPlayActivity(View view) {
        Intent intent = new Intent(this, AccelerometerPlayActivity.class);
        startActivity(intent);
    }
}
