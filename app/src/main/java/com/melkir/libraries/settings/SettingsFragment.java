package com.melkir.libraries.settings;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melkir.libraries.R;
import com.melkir.libraries.util.LocaleHelper;

public class SettingsFragment extends PreferenceFragment {
    public static boolean onBackButtonRecreateView = false;
    private Preference mDisconnect;
    private ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (null != mActionBar) {
            mActionBar.setTitle(R.string.action_settings);
        }

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        mDisconnect = getPreferenceManager().findPreference("disconnect");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (null != user) {
            mDisconnect.setEnabled(true);
            mDisconnect.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getActivity(), "Account disconnected", Toast.LENGTH_SHORT).show();
                            mDisconnect.setEnabled(false);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),
                                    "An error occurred, please check your internet connection",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    return true;
                }
            });
        } else {
            mDisconnect.setOnPreferenceClickListener(null);
            mDisconnect.setEnabled(false);
        }

        String currentLanguage = LocaleHelper.getLanguage(getActivity());
        ListPreference language = (ListPreference) getPreferenceManager().findPreference("language");
        language.setValue(currentLanguage);
        language.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                updateView((String) newValue);
                return true;
            }
        });
    }

    private void updateView(String languageCode) {
        onBackButtonRecreateView = true;
        LocaleHelper.setLocale(getActivity(), languageCode);
        setPreferenceScreen(null);
        addPreferencesFromResource(R.xml.preferences);
        mActionBar.setTitle(R.string.action_settings);
    }

}
