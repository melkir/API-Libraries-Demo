package com.melkir.libraries.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.melkir.libraries.fragment.SettingsFragment;
import com.melkir.libraries.util.LocaleHelper;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (SettingsFragment.onBackButtonRecreateView) {
            SettingsFragment.onBackButtonRecreateView = false;
            LocaleHelper.updateViewNeeded = true;
        }
    }
}
