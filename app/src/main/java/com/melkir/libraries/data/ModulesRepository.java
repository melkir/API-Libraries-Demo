package com.melkir.libraries.data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

import com.melkir.libraries.R;

import java.util.ArrayList;
import java.util.List;

public class ModulesRepository implements ModulesDataSource {
    private static final String TAG = ModulesRepository.class.getSimpleName();
    private List<Module> modules;

    public ModulesRepository(Context context) {
        Log.d(TAG, "newModulesRepository");
        modules = loadModulesFromXML(context);
    }

    private List<Module> loadModulesFromXML(Context context) {
        List<Module> modules = new ArrayList<>();
        Resources resources = context.getResources();
        final String[] mModulesTitle = resources.getStringArray(R.array.modules_title);
        final String[] mModulesDesc = resources.getStringArray(R.array.modules_desc);
        final String[] mModulesCategory = resources.getStringArray(R.array.modules_category);
        final String[] mModulesLink = resources.getStringArray(R.array.modules_link);
        final String[] mModulesAction = resources.getStringArray(R.array.modules_action);
        final TypedArray a = resources.obtainTypedArray(R.array.modules_picture);
        for (int i = 0; i < mModulesTitle.length; ++i) {
            String[] categories = mModulesCategory[i].split(";");
            Module module = new Module(i, mModulesTitle[i], mModulesDesc[i], mModulesLink[i],
                    mModulesAction[i], categories, a.getResourceId(i, R.drawable.card_demo));
            modules.add(module);
        }
        a.recycle();
        return modules;
    }

    @Override
    public List<Module> getModules() {
        return modules;
    }
}
