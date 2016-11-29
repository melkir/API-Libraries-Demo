package com.melkir.googlesamplesdemo.view;

import android.widget.Filter;

import com.melkir.googlesamplesdemo.model.Module;

import java.util.ArrayList;
import java.util.List;

class CardFilter extends Filter {
    private final CardAdapter mAdapter;
    private final List<Module> mOriginalList;
    private final List<Module> mFilteredList;

    CardFilter(CardAdapter cardAdapter, List<Module> modules) {
        this.mAdapter = cardAdapter;
        this.mOriginalList = modules;
        this.mFilteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        mFilteredList.clear();
        final FilterResults results = new FilterResults();
        if (0 == charSequence.length()) {
            mFilteredList.addAll(mOriginalList);
        } else {
            final String filterPattern = charSequence.toString().toLowerCase().trim();
            for (final Module module : mOriginalList) {
                if (this.contains(module.getCategories(), filterPattern)) {
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

    private <T> boolean contains(final T[] array, final T v) {
        if (v == null) {
            for (final T e : array) if (e == null) return true;
        } else {
            for (final T e : array) if (e == v || v.equals(e)) return true;
        }
        return false;
    }

}
