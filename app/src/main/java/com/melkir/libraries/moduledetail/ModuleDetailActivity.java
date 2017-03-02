package com.melkir.libraries.moduledetail;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.melkir.libraries.R;
import com.melkir.libraries.data.Module;
import com.melkir.libraries.databinding.ActivityDetailBinding;

public class ModuleDetailActivity extends AppCompatActivity {

    public static final String MODULE = "MODULE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // bind the module passed given by the parent intent
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Set up the toolbar with the back button and no title
        setupActionBar();

        Module module = getIntent().getParcelableExtra(MODULE);
        binding.setModule(module);

        // TODO Attach the module to a fragment instead of the activity
//        ModuleDetailFragment moduleDetailFragment = (ModuleDetailFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.contentFrame);
//
//        if (moduleDetailFragment != null) {
//            moduleDetailFragment = ModuleDetailFragment.newInstance(MODULE);
//
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
//                    moduleDetailFragment, R.id.contentFrame);
//        }
//
//        new ModuleDetailPresenter(module, moduleDetailFragment);
    }

    @BindingAdapter({"android:background"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
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
