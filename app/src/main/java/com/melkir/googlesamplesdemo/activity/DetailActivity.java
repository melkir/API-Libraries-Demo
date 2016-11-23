package com.melkir.googlesamplesdemo.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.util.ActivityLauncher;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar((Toolbar) findViewById(com.melkir.materialdesigncodelab.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();

        // Set the title on the collapsing toolbar
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(com.melkir.materialdesigncodelab.R.id.collapsing_toolbar);
        String[] modules = resources.getStringArray(R.array.modules);
        collapsingToolbar.setTitle(modules[position % modules.length]);

        // Set the description text
        String[] modulesDesc = resources.getStringArray(R.array.modules_desc);
        TextView moduleDesc = (TextView) findViewById(R.id.module_desc);
        moduleDesc.setText(modulesDesc[position % modulesDesc.length]);

        // Set the module link
        String[] modulesLink = resources.getStringArray(R.array.modules_link);
        TextView moduleLink = (TextView) findViewById(R.id.module_link);
        moduleLink.setText(modulesLink[position % modulesLink.length]);;

        // Set the picture
        TypedArray modulesPictures = resources.obtainTypedArray(R.array.modules_picture);
        ImageView modulePicture = (ImageView) findViewById(R.id.image);
        modulePicture.setImageDrawable(modulesPictures.getDrawable(position % modulesPictures.length()));

        // Set the fab icon action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_action);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityLauncher.start(view.getContext(), position);
            }
        });

        modulesPictures.recycle();

    }

}
