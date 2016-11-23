package com.melkir.googlesamplesdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.melkir.googlesamplesdemo.R;
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
