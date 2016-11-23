package com.melkir.googlesamplesdemo.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.melkir.googlesamplesdemo.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] modules = resources.getStringArray(R.array.modules);
        String[] modulesDesc = resources.getStringArray(R.array.modules_desc);
        TextView moduleDesc = (TextView) findViewById(R.id.module_desc);
        moduleDesc.setText(modulesDesc[position % modulesDesc.length]);

//        TypedArray modulesPictures = resources.obtainTypedArray(R.array.modules_picture);
//        ImageView modulePicture = (ImageView) findViewById(R.id.image);
//        modulePicture.setImageDrawable(modulesPictures.getDrawable(position % modulesPictures.length()));
//
//        modulesPictures.recycle();

    }
}
