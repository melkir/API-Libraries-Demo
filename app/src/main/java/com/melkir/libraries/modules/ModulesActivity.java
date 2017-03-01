package com.melkir.libraries.modules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import com.melkir.libraries.cards.CardsFilterType;
import com.melkir.libraries.data.ModulesRepository;
import com.melkir.libraries.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModulesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private ModulesPresenter mModulesPresenter;

    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.drawer) DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private SearchView mSearchView;
    private View mNavHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNavHeader = mNavigationView.getHeaderView(0);

        configureToolbar();

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

        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            CardsFilterType currentFiltering =
                    (CardsFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mModulesPresenter.loadModules();
            mModulesPresenter.setFiltering(currentFiltering);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CURRENT_FILTERING_KEY, mModulesPresenter.getFiltering());
        super.onSaveInstanceState(outState);
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

    private void configureToolbar() {
        // Adding Toolbar to Main screen
        setSupportActionBar(mToolbar);
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Implement query filter
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO Implement query filter
        return false;
    }

    private class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                // TODO Implement navigation
                case R.id.component:
                    mModulesPresenter.setFiltering(CardsFilterType.COMPONENT);
                    break;
                case R.id.design:
                    mModulesPresenter.setFiltering(CardsFilterType.DESIGN);
                    break;
                case R.id.game:
                    mModulesPresenter.setFiltering(CardsFilterType.GAME);
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
