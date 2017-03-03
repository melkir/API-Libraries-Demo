package com.melkir.libraries.modules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melkir.libraries.BuildConfig;
import com.melkir.libraries.R;
import com.melkir.libraries.about.AboutActivity;
import com.melkir.libraries.data.ModulesRepository;
import com.melkir.libraries.settings.SettingsActivity;
import com.melkir.libraries.util.ActivityUtils;
import com.melkir.libraries.util.LocaleHelper;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.melkir.libraries.modules.ModulesType.COMPONENT;
import static com.melkir.libraries.modules.ModulesType.DESIGN;
import static com.melkir.libraries.modules.ModulesType.GAME;

public class ModulesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = ModulesActivity.class.getSimpleName();

    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private static final int RC_SIGN_IN = 9001;
    private ModulesPresenter mModulesPresenter;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private SearchView mSearchView;
    private View mNavHeader;
    private TextView mUsername;
    private TextView mEmail;
    private ImageView mProfilePicture;
    private Button mSignInButton;
    private Button mSignOutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configureToolbar();

        // Set the behavior of the navigation drawer
        mNavigationView.setNavigationItemSelectedListener(new NavigationViewListener());
        mSignInButton = (Button) mNavigationView.findViewById(R.id.sign_in_button);
        mSignOutButton = (Button) mNavigationView.findViewById(R.id.sign_out_button);

        // Set our navigation drawer header
        mNavHeader = mNavigationView.getHeaderView(0);
        mUsername = (TextView) mNavHeader.findViewById(R.id.name);
        mEmail = (TextView) mNavHeader.findViewById(R.id.email);
        mProfilePicture = (ImageView) mNavHeader.findViewById(R.id.profile_picture);

        ModulesFragment modulesFragment = (ModulesFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (modulesFragment == null) {
            // Create the fragment
            modulesFragment = ModulesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), modulesFragment, R.id.container);
        }

        // Add counter on menu item
        initMenuCounters();

        // Create the presenter
        mModulesPresenter = new ModulesPresenter(new ModulesRepository(this), modulesFragment);

        // Update the UI if the user is logged
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) showUserOnNavHeader(user);
                else showNoUserOnNavHeader();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CURRENT_FILTERING_KEY, mModulesPresenter.getFiltering());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!LocaleHelper.updateViewNeeded) return;
        mDrawerLayout.closeDrawer(GravityCompat.START);
        recreate();
        LocaleHelper.updateViewNeeded = false;
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        final ModulesType currentFiltering = (ModulesType) bundle.getSerializable(CURRENT_FILTERING_KEY);
        // TODO Replace this part with an Observable to apply filtering after getModules have finished
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mModulesPresenter.setFiltering(currentFiltering);
            }
        }, 100);

        SubMenu menu = mNavigationView.getMenu().getItem(0).getSubMenu();
        if (COMPONENT == currentFiltering) {
            menu.findItem(R.id.component).setChecked(true);
        } else if (DESIGN == currentFiltering) {
            menu.findItem(R.id.design).setChecked(true);
        } else if (GAME == currentFiltering) {
            menu.findItem(R.id.game).setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModulesPresenter.setDisplayTypeSearch();
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mModulesPresenter.setDisplayTypeCards();
                return false;
            }
        });

        return true;
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mModulesPresenter.setFiltering(query);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "User logged in");
        } else if (resultCode == RESULT_CANCELED) {
            showSnackbar(getString(R.string.sign_in_cancelled));
        } else if (resultCode == ErrorCodes.NO_NETWORK) {
            showSnackbar(getString(R.string.no_internet_connection));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean flipState = !item.isChecked();

            for (int i = 0; i < 3; i++) {
                MenuItem top = mNavigationView.getMenu().getItem(0).getSubMenu().getItem(i);
                if (top.isChecked()) top.setChecked(false);
            }

            switch (item.getItemId()) {
                case R.id.component:
                    mModulesPresenter.setFiltering(COMPONENT);
                    item.setChecked(flipState);
                    break;
                case R.id.design:
                    mModulesPresenter.setFiltering(DESIGN);
                    item.setChecked(flipState);
                    break;
                case R.id.game:
                    mModulesPresenter.setFiltering(GAME);
                    item.setChecked(flipState);
                    break;
                case R.id.settings:
                    Intent settingsIntent = new Intent(getApplication(), SettingsActivity.class);
                    startActivity(settingsIntent);
                    break;
                case R.id.about:
                    Intent aboutIntent = new Intent(getApplication(), AboutActivity.class);
                    startActivity(aboutIntent);
                    break;
                default:
                    Toast.makeText(mNavHeader.getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
                    return true;
            }
            mDrawerLayout.closeDrawers();
            return true;
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
        AuthUI.getInstance().signOut(this);
    }

    private void showUserOnNavHeader(FirebaseUser user) {
        // show sign-out button, hide the sign-in button
        mSignInButton.setVisibility(View.GONE);
        mSignOutButton.setVisibility(View.VISIBLE);
        mUsername.setText(user.getDisplayName());
        mEmail.setText(user.getEmail());
        // load the user profile picture
        Glide.with(this).load(user.getPhotoUrl()).into(mProfilePicture);
    }

    private void showNoUserOnNavHeader() {
        mSignInButton.setVisibility(View.VISIBLE);
        mSignOutButton.setVisibility(View.GONE);
        mUsername.setText("");
        mEmail.setText("");
        mProfilePicture.setImageResource(R.drawable.ic_account);
    }

    private void showSnackbar(String message) {
        Snackbar.make(mDrawerLayout, message, Snackbar.LENGTH_SHORT);
    }

    private void initMenuCounters() {
        setMenuCounter(R.id.component, 6);
        setMenuCounter(R.id.design, 2);
        setMenuCounter(R.id.game, 3);
    }

    private void setMenuCounter(@IdRes int itemId, int count) {
        TextView view = (TextView) mNavigationView.getMenu().findItem(itemId).getActionView();
        view.setText(count > 0 ? String.valueOf(count) : null);
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
}
