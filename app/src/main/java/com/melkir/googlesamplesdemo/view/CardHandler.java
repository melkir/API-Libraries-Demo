package com.melkir.googlesamplesdemo.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.melkir.googlesamplesdemo.activity.DetailActivity;
import com.melkir.googlesamplesdemo.model.Module;

public class CardHandler {

    public void onCardClick(View view, Module module) {
        final Context context = view.getContext();
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.MODULE, module);
        context.startActivity(intent);
    }

}
