package com.melkir.libraries.moduledetail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.melkir.libraries.R;
import com.melkir.libraries.data.Module;
import com.melkir.libraries.util.ActivityUtils;

public class ModuleDetailActivity extends AppCompatActivity {

    public static final String MODULE = "MODULE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        // Set up the toolbar with the back button and no title
        setupActionBar();

        Module module = getIntent().getParcelableExtra(MODULE);
        ModuleDetailFragment moduleDetailFragment = (ModuleDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (moduleDetailFragment == null) {
            moduleDetailFragment = ModuleDetailFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), moduleDetailFragment, R.id.contentFrame);
        }

        new ModuleDetailPresenter(module, moduleDetailFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
    }
}
