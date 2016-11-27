package com.melkir.googlesamplesdemo.view;

import android.widget.Filter;

import com.melkir.googlesamplesdemo.model.Module;

import java.util.List;

public class CardFilter extends Filter {
    private List<Module> mModules;
    private String[] mFilteredModules;

    public CardFilter(CardAdapter cardAdapter, List<Module> modules) {
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        return null;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
    }

}
