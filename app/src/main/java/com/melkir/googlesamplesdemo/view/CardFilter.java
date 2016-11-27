package com.melkir.googlesamplesdemo.view;

import android.widget.Filter;

public class CardFilter extends Filter {
    private String[] mModules;
    private String[] mFilteredModules;

    public CardFilter(CardAdapter cardAdapter, String[] modules) {
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        return null;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

    }
}
