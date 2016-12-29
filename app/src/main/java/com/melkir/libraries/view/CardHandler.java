package com.melkir.libraries.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.melkir.libraries.activity.DetailActivity;
import com.melkir.libraries.model.Module;

public class CardHandler {

    public void onCardClick(View view, Module module) {
        final Context context = view.getContext();
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.MODULE, module);
        context.startActivity(intent);
    }

}
