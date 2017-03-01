package com.melkir.libraries.modules.adapters;

import android.widget.Filter;

import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesType;

import java.util.ArrayList;
import java.util.List;

class CardsFilter extends Filter {
    private static final String TAG = CardsFilter.class.getSimpleName();

    private final CardsAdapter mAdapter;
    private final List<Module> mFilteredList;

    private List<Module> mOriginalList;

    public CardsFilter(CardsAdapter cardsAdapter, List<Module> modules) {
        this.mAdapter = cardsAdapter;
        this.mOriginalList = modules;
        this.mFilteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        mFilteredList.clear();
        final FilterResults results = new FilterResults();
        final String filter = charSequence.toString().toLowerCase().trim();
        if (filter.equals(ModulesType.ALL_CATEGORIES.toString()) || 0 == filter.length()) {
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

    private <T> boolean contains(final T[] array, final T v) {
        if (v == null) {
            for (final T e : array) if (e == null) return true;
        } else {
            for (final T e : array) if (e == v || v.equals(e)) return true;
        }
        return false;
    }

    void notifyDataChanged(List<Module> originalList) {
        this.mOriginalList = new ArrayList<>(originalList);
    }
}
