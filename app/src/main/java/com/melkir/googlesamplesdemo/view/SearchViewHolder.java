package com.melkir.googlesamplesdemo.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class SearchViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    SearchViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    ViewDataBinding getBinding() {
        return binding;
    }

}
