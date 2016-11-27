package com.melkir.googlesamplesdemo.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.model.Module;
import com.melkir.googlesamplesdemo.util.ActivityLauncher;

public class DetailActivity extends AppCompatActivity {

    public static final String MODULE = "module";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Retrieve module from extra
        final Module module = getIntent().getParcelableExtra(MODULE);

        // Set the title on the collapsing toolbar
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(module.getTitle());

        // Set the description text
        TextView moduleDesc = (TextView) findViewById(R.id.module_desc);
        moduleDesc.setText(module.getDescription());

        // Set the module link
        TextView moduleLink = (TextView) findViewById(R.id.module_link);
        moduleLink.setText(module.getLink());

        // Set the picture
        ImageView modulePicture = (ImageView) findViewById(R.id.image);
        modulePicture.setImageResource(module.getPicture());

        // Set the fab icon action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_action);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityLauncher.start(view.getContext(), module.getAction());
            }
        });
    }

}
