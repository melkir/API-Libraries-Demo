package com.melkir.googlesamplesdemo.view;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.melkir.googlesamplesdemo.BR;
import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.model.Module;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements Filterable {

    public static final String TAG = CardAdapter.class.getSimpleName();

    private List<Module> mModules;
    private final CardFilter cardFilter;

    /**
     * Adapter to display recycler view.
     */
    public CardAdapter(List<Module> modules) {
        this.mModules = modules;
        this.cardFilter = new CardFilter(this, mModules);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Module module = mModules.get(position);
        holder.getBinding().setVariable(BR.module, module);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    @Override
    public Filter getFilter() {
        return cardFilter;
    }

    public void setList(List<Module> list) {
        this.mModules = list;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
