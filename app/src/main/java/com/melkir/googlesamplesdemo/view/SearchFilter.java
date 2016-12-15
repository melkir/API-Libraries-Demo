package com.melkir.googlesamplesdemo.view;

import android.widget.Filter;

import com.melkir.googlesamplesdemo.model.Module;

import java.util.ArrayList;
import java.util.List;

class SearchFilter extends Filter {
    private final SearchAdapter mAdapter;
    private final List<Module> mOriginalList;
    private final List<Module> mFilteredList;

    SearchFilter(SearchAdapter mAdapter, List<Module> mOriginalList) {
        this.mAdapter = mAdapter;
        this.mOriginalList = mOriginalList;
        this.mFilteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        mFilteredList.clear();
        final FilterResults results = new FilterResults();
        if (0 == charSequence.length()) {
            mFilteredList.addAll(mOriginalList);
        } else {
            final String filterPattern = charSequence.toString().toLowerCase();
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
        mAdapter.setList(mFilteredList);
        mAdapter.notifyDataSetChanged();
    }
}
