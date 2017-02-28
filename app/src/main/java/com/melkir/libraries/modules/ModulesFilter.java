package com.melkir.libraries.modules;

import android.util.Log;
import android.widget.Filter;

import com.melkir.libraries.model.Module;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModulesFilter extends Filter {
    public static final String TAG = ModulesFilter.class.getSimpleName();

    private final ModulesFragment.ModulesAdapter mAdapter;
    private final List<Module> mFilteredList;

    private List<Module> mOriginalList;

    public ModulesFilter(ModulesFragment.ModulesAdapter modulesAdapter, List<Module> modules) {
        this.mAdapter = modulesAdapter;
        this.mOriginalList = new LinkedList<>(modules);
        this.mFilteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        mFilteredList.clear();
        final FilterResults results = new FilterResults();
        final String filter = charSequence.toString().toLowerCase().trim();
        Log.d(TAG, "Size: " + mOriginalList.size());
        if (filter.equals(ModuleFilterType.ALL_CATEGORIES.toString()) || 0 == filter.length()) {
            mFilteredList.addAll(mOriginalList);
        } else {
            for (final Module module : mOriginalList) {
                if (this.contains(module.getCategories(), filter)) {
                    mFilteredList.add(module);
                }
            }
        }
        results.values = mFilteredList;
        results.count = mFilteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        mAdapter.setList(mFilteredList);
        mAdapter.notifyDataSetChanged();
    }

    void setOriginalList(List<Module> originalList) {
        this.mOriginalList = originalList;
    }

    private <T> boolean contains(final T[] array, final T v) {
        if (v == null) {
            for (final T e : array) if (e == null) return true;
        } else {
            for (final T e : array) if (e == v || v.equals(e)) return true;
        }
        return false;
    }

}
