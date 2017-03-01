package com.melkir.libraries.modules.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.melkir.libraries.R;
import com.melkir.libraries.databinding.SearchBinding;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesFragment;

import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchAdapter extends SortedListAdapter<Module> implements Filterable {
    private static final String TAG = SearchAdapter.class.getSimpleName();

    private final ModulesFragment.ModuleItemListener mItemListener;
    private SearchFilter mSearchFilter;
    private List<Module> mModules;

    public SearchAdapter(Context context, List<Module> modules, ModulesFragment.ModuleItemListener itemListener) {
        super(context, Module.class, TITLE_COMPARATOR);
        setList(modules);
        mItemListener = itemListener;
        mSearchFilter = new SearchFilter(this, mModules);
    }

    @Override
    public SearchFilter getFilter() {
        return mSearchFilter;
    }

    @Override
    protected ViewHolder<? extends Module> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int i) {
        final SearchBinding binding = SearchBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding, mItemListener);
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
        setList(modules);
//        mSearchFilter.notifyDataChanged(modules);
        notifyDataSetChanged();
    }

    public void setList(List<Module> modules) {
        mModules = checkNotNull(modules);
    }

    private static final Comparator<Module> TITLE_COMPARATOR = new Comparator<Module>() {
        @Override
        public int compare(Module a, Module b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    };

    class SearchViewHolder extends SortedListAdapter.ViewHolder<Module> {
        private final ImageView picture;
        private final SearchBinding binding;
        private ModulesFragment.ModuleItemListener listener;

        public SearchViewHolder(SearchBinding binding, ModulesFragment.ModuleItemListener listener) {
            super(binding.getRoot());
            picture = (ImageView) itemView.findViewById(R.id.item_avatar);
            this.binding = binding;
            this.listener = listener;
        }

        @Override
        protected void performBind(final Module module) {
            Glide.with(picture.getContext()).load(module.getPicture()).into(picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onModuleClick(module);
                }
            });
            binding.setModule(module);
        }
    }
}
