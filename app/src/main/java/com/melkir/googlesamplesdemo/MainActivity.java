package com.melkir.googlesamplesdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.melkir.googlesamplesdemo.fragment.CardContentFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction()
                .add(R.id.activity_main, new CardContentFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void startMaterialDesignActivity(View view) {
//        Intent intent = new Intent(this, MaterialDesignActivity.class);
//        startActivity(intent);
//    }
//
//    public void startBarcodeReaderActivity(View view) {
//        Intent intent = new Intent(this, BarcodeReaderActivity.class);
//        startActivity(intent);
//    }
//
//    public void startFaceTrackerActivity(View view) {
//        Intent intent = new Intent(this, FaceTrackerActivity.class);
//        startActivity(intent);
//    }
//
//    public void startGooglyEyesActivity(View view) {
//        Intent intent = new Intent(this, GooglyEyesActivity.class);
//        startActivity(intent);
//    }
//
//    public void startAccelerometerPlayActivity(View view) {
//        Intent intent = new Intent(this, AccelerometerPlayActivity.class);
//        startActivity(intent);
//    }
//
//    public void startOurStreetsActivity(View view) {
//        Intent intent = new Intent(this, OurStreetsActivity.class);
//        startActivity(intent);
//    }
//
//    public void startAIExperimentsActivity(View view) {
//        String url = "https://aiexperiments.withgoogle.com";
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        startActivity(intent);
//    }
//
//    public void startHalloweenDoodleActivity(View view) {
//        String url = "https://www.google.com/doodles/halloween-2016";
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        startActivity(intent);
//    }
//
//    public void startTextToSpeechActivity(View view) {
//        Intent intent = new Intent(this, TextToSpeechActivity.class);
//        startActivity(intent);
//    }

}
