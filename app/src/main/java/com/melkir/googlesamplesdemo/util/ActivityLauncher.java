package com.melkir.googlesamplesdemo.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.melkir.accelerometerplay.AccelerometerPlayActivity;
import com.melkir.materialdesigncodelab.MaterialDesignActivity;
import com.melkir.ourstreets.activity.OurStreetsActivity;
import com.melkir.texttospeech.TextToSpeechActivity;
import com.melkir.vision.barcodereader.BarcodeReaderActivity;
import com.melkir.vision.facetracker.FaceTrackerActivity;
import com.melkir.vision.googlyeyes.GooglyEyesActivity;

public class ActivityLauncher {

    public static void start(Context context, int position) {
        Class<?> activity;
        switch (position) {
            case 0:
                activity = BarcodeReaderActivity.class;
                break;
            case 1:
                activity = FaceTrackerActivity.class;
                break;
            case 2:
                activity = GooglyEyesActivity.class;
                break;
            case 3:
                activity = AccelerometerPlayActivity.class;
                break;
            case 4:
                activity = OurStreetsActivity.class;
                break;
            case 5:
                activity = MaterialDesignActivity.class;
                break;
            case 6:
                activity = TextToSpeechActivity.class;
                break;
            default:
                Toast.makeText(context, "Not implemented yet", Toast.LENGTH_LONG).show();
                return;
        }
        context.startActivity(new Intent(context, activity));
    }

}
