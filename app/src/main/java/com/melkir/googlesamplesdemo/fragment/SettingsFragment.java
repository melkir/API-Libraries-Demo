package com.melkir.googlesamplesdemo.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.melkir.googlesamplesdemo.R;

public class SettingsFragment extends PreferenceFragment {

    Preference mDisconnect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle(R.string.action_settings);
        }

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        mDisconnect = getPreferenceManager().findPreference("disconnect");
        if (null != FirebaseAuth.getInstance().getCurrentUser()) {
            mDisconnect.setEnabled(true);
            mDisconnect.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {
                    AuthUI.getInstance().delete(getActivity()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            displayTaskResult(task);
                        }
                    });
                    return true;
                }
            });
        } else {
            mDisconnect.setOnPreferenceClickListener(null);
            mDisconnect.setEnabled(false);
        }
    }

    private void displayTaskResult(Task<Void> task) {
        if (task.isSuccessful()) {
            Toast.makeText(getActivity(), "Account disconnected", Toast.LENGTH_SHORT).show();
            mDisconnect.setEnabled(false);
        } else {
            Toast.makeText(getActivity(), "An error occurred, please check your internet connection", Toast.LENGTH_LONG).show();
        }
    }

}
