package com.melkir.googlesamplesdemo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.melkir.googlesamplesdemo.BR;
import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.model.Module;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements Filterable {

    public static final String TAG = CardAdapter.class.getSimpleName();

    private List<Module> mModules;
    private final CardFilter cardFilter;
    private final Context mContext;
    private CardHandler mHandler;

    /**
     * Adapter to display recycler view.
     */
    public CardAdapter(Context context, List<Module> modules) {
        this.mContext = context;
        this.mModules = modules;
        this.mHandler = new CardHandler();
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
        Glide.with(mContext).load(module.getPicture()).into(holder.getPicture());
//        holder.getPicture().setImageResource(module.getPicture());
        holder.getBinding().setVariable(BR.module, module);
        holder.getBinding().setVariable(BR.handler, mHandler);
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

}
