package com.melkir.googlesamplesdemo.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.melkir.googlesamplesdemo.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar((Toolbar) findViewById(com.melkir.materialdesigncodelab.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
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

        // Set the picture
        TypedArray modulesPictures = resources.obtainTypedArray(R.array.modules_picture);
        ImageView modulePicture = (ImageView) findViewById(R.id.image);
        modulePicture.setImageDrawable(modulesPictures.getDrawable(position % modulesPictures.length()));

        modulesPictures.recycle();

    }
}
