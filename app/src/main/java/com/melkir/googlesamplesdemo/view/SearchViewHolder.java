package com.melkir.googlesamplesdemo.view;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.melkir.googlesamplesdemo.databinding.SearchBinding;
import com.melkir.googlesamplesdemo.model.Module;

class SearchViewHolder extends SortedListAdapter.ViewHolder<Module> {
    private final SearchBinding mBinding;

    SearchViewHolder(SearchBinding binding, SearchAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);
        mBinding = binding;
    }

    @Override
    protected void performBind(Module module) {
        mBinding.setModule(module);
    }

}
