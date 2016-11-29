package com.melkir.googlesamplesdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melkir.googlesamplesdemo.BuildConfig;
import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.fragment.CardContentFragment;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private CardContentFragment cardContentFragment;
    private String mCurrentFilter = "";
    private static final String STATE_FILTER = "filterSelected";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding Toolbar to Main screen
        addToolbar();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(new NavigationViewListener());
        // Add our card fragment
        cardContentFragment = new CardContentFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, cardContentFragment).commit();
        // Update the UI if the user is logged
        updateUI(FirebaseAuth.getInstance().getCurrentUser());
    }

    private void updateUI(FirebaseUser user) {
        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        Button signOutButton = (Button) findViewById(R.id.sign_out_button);
        if (user != null) {
            // show sign-out button, hide the sign-in button
            signInButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            signInButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the filter
        outState.putString(STATE_FILTER, mCurrentFilter);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the filter
        mCurrentFilter = savedInstanceState.getString(STATE_FILTER);
        // Apply the filter to the view
        cardContentFragment.filter(mCurrentFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToolbar() {
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(),
                    R.drawable.ic_menu, getTheme());
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.component:
                    filterBehaviour(item, "component");
                    break;
                case R.id.design:
                    filterBehaviour(item, "design");
                    break;
                case R.id.game:
                    filterBehaviour(item, "game");
                    break;
                case R.id.feedback:
                case R.id.about:
                case R.id.settings:
                default:
                    Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
                    break;
            }
            mDrawerLayout.closeDrawers();
            return true;
        }

        private void filterBehaviour(MenuItem item, String constraint) {
            if (!item.isChecked()) {
                item.setChecked(true);
                mCurrentFilter = constraint;
                cardContentFragment.filter(constraint);
            } else {
                item.setChecked(false);
                mCurrentFilter = "";
                cardContentFragment.filter("");
            }
        }
    }

    public void signIn(View view) {
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())
                )
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .setTheme(R.style.AppTheme)
                .build(), RC_SIGN_IN);
    }

    public void signOut(View view) {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                // user is now signed out
                updateUI(null);
            }
        });
    }

    private void showSnackbar(String message) {
        Snackbar.make(mDrawerLayout, message, Snackbar.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Sign in canceled
        if (resultCode == RESULT_CANCELED) {
            showSnackbar(getString(R.string.sign_in_cancelled));
            return;
        } else if (resultCode == ResultCodes.RESULT_NO_NETWORK) {
            showSnackbar(getString(R.string.no_internet_connection));
        }
        updateUI(FirebaseAuth.getInstance().getCurrentUser());
    }
}
