package com.melkir.googlesamplesdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.melkir.accelerometerplay.AccelerometerPlayActivity;
import com.melkir.materialdesigncodelab.MaterialDesignActivity;
import com.melkir.ourstreets.activity.OurStreetsActivity;
import com.melkir.texttospeech.TextToSpeechActivity;
import com.melkir.vision.barcodereader.BarcodeReaderActivity;
import com.melkir.vision.facetracker.FaceTrackerActivity;
import com.melkir.vision.googlyeyes.GooglyEyesActivity;

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

    public void startOurStreetsActivity(View view) {
        Intent intent = new Intent(this, OurStreetsActivity.class);
        startActivity(intent);
    }

    public void startMaterialDesignActivity(View view) {
        Intent intent = new Intent(this, MaterialDesignActivity.class);
        startActivity(intent);
    }

    public void startAIExperimentsActivity(View view) {
        String url = "https://aiexperiments.withgoogle.com";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void startHalloweenDoodleActivity(View view) {
        String url = "https://www.google.com/doodles/halloween-2016";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void startTextToSpeechActivity(View view) {
        Intent intent = new Intent(this, TextToSpeechActivity.class);
        startActivity(intent);
    }

}
