package com.melkir.libraries.modules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.melkir.libraries.R;
import com.melkir.libraries.data.ModulesRepository;
import com.melkir.libraries.util.ActivityUtils;

public class ModulesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ModulesPresenter mModulesPresenter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private View mNavHeader;
    private SearchView mSearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar((Toolbar) findViewById(R.id.toolbar));

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mNavHeader = mNavigationView.getHeaderView(0);

        // Set the behavior of the navigation drawer
        mNavigationView.setNavigationItemSelectedListener(new NavigationViewListener());

        ModulesFragment modulesFragment = (ModulesFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (modulesFragment == null) {
            // Create the fragment
            modulesFragment = ModulesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), modulesFragment, R.id.container);
        }

        // Create the presenter
        mModulesPresenter = new ModulesPresenter(new ModulesRepository(this), modulesFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar(Toolbar toolbar) {
        // Adding Toolbar to Main screen
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Implement filter
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO Implement filter
        return false;
    }

    private class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                // TODO Implement navigation
                case R.id.component:
                    break;
                case R.id.design:
                    break;
                case R.id.game:
                    break;
                case R.id.settings:
                    break;
                case R.id.about:
                    break;
                default:
                    Toast.makeText(mNavHeader.getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
                    return true;
            }
            mDrawerLayout.closeDrawers();
            return true;
        }
    }

}
