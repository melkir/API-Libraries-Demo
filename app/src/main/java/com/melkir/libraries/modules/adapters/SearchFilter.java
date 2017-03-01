package com.melkir.libraries.modules.adapters;

import android.widget.Filter;

import com.melkir.libraries.model.Module;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter extends Filter {
    private static final String TAG = SearchFilter.class.getSimpleName();

    private final SearchAdapter mAdapter;
    private final List<Module> mFilteredList;
    private List<Module> mOriginalList;

    SearchFilter(SearchAdapter searchAdapter, List<Module> modules) {
        this.mAdapter = searchAdapter;
        this.mOriginalList = modules;
        this.mFilteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        mFilteredList.clear();
        final FilterResults results = new FilterResults();
        if (0 == constraint.length()) {
            mFilteredList.addAll(mOriginalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();
            for (final Module module : mOriginalList) {
                if (module.getTitle().toLowerCase().contains(filterPattern)) {
                    mFilteredList.add(module);
                }
            }
        }
        results.values = mFilteredList;
        results.count = mFilteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mAdapter.edit().replaceAll(mFilteredList).commit();
    }

    void notifyDataChanged(List<Module> originalList) {
        this.mOriginalList = new ArrayList<>(originalList);
    }
}