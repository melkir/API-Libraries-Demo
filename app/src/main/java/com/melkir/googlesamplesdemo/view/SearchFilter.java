package com.melkir.googlesamplesdemo.view;

import android.widget.Filter;

import com.melkir.googlesamplesdemo.model.Module;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter extends Filter {
    private final SearchAdapter mAdapter;
    private final List<Module> mOriginalList;
    private final List<Module> mFilteredList;

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
}
