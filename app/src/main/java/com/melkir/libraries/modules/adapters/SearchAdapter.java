package com.melkir.libraries.modules.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.melkir.libraries.data.Module;
import com.melkir.libraries.databinding.SearchBinding;
import com.melkir.libraries.modules.ModulesFragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchAdapter extends SortedListAdapter<Module> {
    private static final String TAG = SearchAdapter.class.getSimpleName();

    private final ModulesFragment.ModuleItemListener mItemListener;

    private List<Module> mDefaultList;

    public SearchAdapter(Context context, List<Module> modules, ModulesFragment.ModuleItemListener itemListener) {
        super(context, Module.class, TITLE_COMPARATOR);
        setList(modules);
        mItemListener = itemListener;
    }

    @Override
    protected ViewHolder<? extends Module> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int i) {
        final SearchBinding binding = SearchBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    protected boolean areItemsTheSame(Module item1, Module item2) {
        return item1.getId() == item2.getId();
    }

    @Override
    protected boolean areItemContentsTheSame(Module oldItem, Module newItem) {
        return oldItem.equals(newItem);
    }

    public void replaceData(List<Module> modules) {
        mDefaultList = modules;
        edit().replaceAll(modules).commit();
    }

    public void setList(List<Module> modules) {
        mDefaultList = checkNotNull(modules);
    }

    public void filter(String requestTitle) {
        List<Module> mFilteredList = new ArrayList<>();
        if (0 == requestTitle.length()) {
            mFilteredList.addAll(mDefaultList);
        } else {
            String title = requestTitle.toLowerCase();
            for (Module module : mDefaultList) {
                if (module.getTitle().toLowerCase().contains(title)) {
                    mFilteredList.add(module);
                }
            }
        }
        edit().replaceAll(mFilteredList).commit();
    }

    private static final Comparator<Module> TITLE_COMPARATOR = new Comparator<Module>() {
        @Override
        public int compare(Module a, Module b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    };

    private class SearchViewHolder extends SortedListAdapter.ViewHolder<Module> {
        private final SearchBinding binding;

        SearchViewHolder(SearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void performBind(final Module module) {
            ImageView avatar = binding.itemAvatar;
            Glide.with(avatar.getContext()).load(module.getPicture()).into(avatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onModuleClick(module);
                }
            });
            binding.setModule(module);
        }
    }
}
