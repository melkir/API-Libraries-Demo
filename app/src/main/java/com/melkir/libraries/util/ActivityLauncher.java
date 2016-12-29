package com.melkir.libraries.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.melkir.accelerometerplay.AccelerometerPlayActivity;
import com.melkir.materialdesigncodelabs.MaterialDesignActivity;
import com.melkir.ourstreets.activity.OurStreetsActivity;
import com.melkir.texttospeech.TextToSpeechActivity;
import com.melkir.vision.barcodereader.BarcodeReaderActivity;
import com.melkir.vision.facetracker.FaceTrackerActivity;
import com.melkir.vision.googlyeyes.GooglyEyesActivity;

public class ActivityLauncher {

    public static void start(Context context, String action) {
        Intent intent = new Intent();
        switch (action) {
            case "barcode":
                intent.setClass(context, BarcodeReaderActivity.class);
                break;
            case "face":
                intent.setClass(context, FaceTrackerActivity.class);
                break;
            case "googly":
                intent.setClass(context, GooglyEyesActivity.class);
                break;
            case "accelerometer":
                intent.setClass(context, AccelerometerPlayActivity.class);
                break;
            case "ourStreets":
                intent.setClass(context, OurStreetsActivity.class);
                break;
            case "materialDesign":
                intent.setClass(context, MaterialDesignActivity.class);
                break;
            case "tts":
                intent.setClass(context, TextToSpeechActivity.class);
                break;
            case "aiExperiments":
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://aiexperiments.withgoogle.com/"));
                break;
            case "halloweenDoodle":
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/doodles/halloween-2016"));
                break;
            default:
                Toast.makeText(context, "Not implemented yet", Toast.LENGTH_LONG).show();
                return;
        }
        context.startActivity(intent);
    }

}
