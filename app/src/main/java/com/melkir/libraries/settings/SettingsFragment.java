package com.melkir.libraries.settings;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
            mDisconnect.setOnPreferenceClickListener(arg0 -> {
                user.delete().addOnCompleteListener(task -> {
                    Toast.makeText(getActivity(), "Account disconnected", Toast.LENGTH_SHORT).show();
                    mDisconnect.setEnabled(false);
                }).addOnFailureListener(e -> Toast.makeText(getActivity(),
                        "An error occurred, please check your internet connection",
                        Toast.LENGTH_LONG).show()
                );
                return true;
            });
        } else {
            mDisconnect.setOnPreferenceClickListener(null);
            mDisconnect.setEnabled(false);
        }

        String currentLanguage = LocaleHelper.getLanguage(getActivity());
        ListPreference language = (ListPreference) getPreferenceManager().findPreference("language");
        language.setValue(currentLanguage);
        language.setOnPreferenceChangeListener((preference, newValue) -> {
            updateView((String) newValue);
            return true;
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
