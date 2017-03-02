package com.melkir.libraries.activity;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.melkir.libraries.BR;
import com.melkir.libraries.R;
import com.melkir.libraries.data.Module;

public class DetailActivity extends AppCompatActivity {

    public static final String MODULE = "module";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Enable back button
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Retrieve module from extra
        final Module module = getIntent().getParcelableExtra(MODULE);

        // Bind module data
        binding.setVariable(BR.module, module);
    }

    @BindingAdapter({"android:background"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); //Call the back button's method
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
