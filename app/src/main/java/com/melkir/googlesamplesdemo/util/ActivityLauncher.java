package com.melkir.googlesamplesdemo.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(context, BarcodeReaderActivity.class);
                break;
            case 1:
                intent.setClass(context, FaceTrackerActivity.class);
                break;
            case 2:
                intent.setClass(context, GooglyEyesActivity.class);
                break;
            case 3:
                intent.setClass(context, AccelerometerPlayActivity.class);
                break;
            case 4:
                intent.setClass(context, OurStreetsActivity.class);
                break;
            case 5:
                intent.setClass(context, MaterialDesignActivity.class);
                break;
            case 6:
                intent.setClass(context, TextToSpeechActivity.class);
                break;
            case 7:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://aiexperiments.withgoogle.com/"));
                break;
            case 8:
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
