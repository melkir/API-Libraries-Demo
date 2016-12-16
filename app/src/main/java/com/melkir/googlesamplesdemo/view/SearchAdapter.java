package com.melkir.googlesamplesdemo.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.melkir.googlesamplesdemo.activity.DetailActivity;
import com.melkir.googlesamplesdemo.databinding.SearchBinding;
import com.melkir.googlesamplesdemo.model.Module;

import java.util.Comparator;
import java.util.List;

public class SearchAdapter extends SortedListAdapter<Module> implements Filterable {

    public interface Listener {
        void onModuleClicked(Module module);
    }

    private final Listener mListener;
    private final SearchFilter mSearchFilter;

    public SearchAdapter(final Context context, List<Module> modules) {
        super(context, Module.class, TITLE_COMPARATOR);
        this.mSearchFilter = new SearchFilter(this, modules);
        mListener = new Listener() {
            @Override
            public void onModuleClicked(Module module) {
                final Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.MODULE, module);
                context.startActivity(intent);
            }
        };
    }

    @Override
    protected ViewHolder<? extends Module> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int i) {
        final SearchBinding binding = SearchBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding, mListener);
    }

    @Override
    protected boolean areItemsTheSame(Module item1, Module item2) {
        return item1.getId() == item2.getId();
    }

    @Override
    protected boolean areItemContentsTheSame(Module oldItem, Module newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public SearchFilter getFilter() {
        return mSearchFilter;
    }

    private static final Comparator<Module> TITLE_COMPARATOR = new Comparator<Module>() {
        @Override
        public int compare(Module a, Module b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    };

}
