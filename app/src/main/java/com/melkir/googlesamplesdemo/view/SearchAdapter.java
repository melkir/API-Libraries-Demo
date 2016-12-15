package com.melkir.googlesamplesdemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.melkir.googlesamplesdemo.BR;
import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.model.Module;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> implements Filterable {

    private List<Module> mModules;
    private final SearchFilter searchFilter;

    public SearchAdapter(List<Module> modules) {
        this.mModules = modules;
        this.searchFilter = new SearchFilter(this, mModules);
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Module module = mModules.get(position);
        holder.getBinding().setVariable(BR.module, module);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public void setList(List<Module> list) {
        this.mModules = list;
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }
}
