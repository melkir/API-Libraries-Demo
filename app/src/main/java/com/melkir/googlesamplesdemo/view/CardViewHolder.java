package com.melkir.googlesamplesdemo.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.melkir.googlesamplesdemo.R;

class CardViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    private final ImageView picture;

    CardViewHolder(View itemView) {
        super(itemView);
        picture = (ImageView) itemView.findViewById(R.id.card_image);
        binding = DataBindingUtil.bind(itemView);
    }

    ImageView getPicture() {
        return picture;
    }

    ViewDataBinding getBinding() {
        return binding;
    }
}
