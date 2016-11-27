package com.melkir.googlesamplesdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.fragment.CardContentFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private CardContentFragment cardContentFragment;

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
                    break;
                case R.id.about:
                    break;
                case R.id.settings:
                    break;
                default:
                    break;
            }
            mDrawerLayout.closeDrawers();
            return true;
        }

        private void filterBehaviour(MenuItem item, String constraint) {
            if (!item.isChecked()) {
                item.setChecked(true);
                cardContentFragment.filter(constraint);
            } else {
                item.setChecked(false);
                cardContentFragment.filter("");
            }
        }
    }

}
