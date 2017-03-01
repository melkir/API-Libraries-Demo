package com.melkir.libraries.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.melkir.libraries.R;
import com.melkir.libraries.databinding.SearchBinding;
import com.melkir.libraries.model.Module;

public class SearchViewHolder extends SortedListAdapter.ViewHolder<Module> {
    private final SearchBinding binding;
    private final ImageView picture;
    private final Context context;

    SearchViewHolder(SearchBinding binding, SearchAdapter.Listener listener) {
        super(binding.getRoot());
//        binding.setListener(listener);
        this.binding = binding;
        final View rootView = binding.getRoot();
        this.context = rootView.getContext();
        this.picture = (ImageView) rootView.findViewById(R.id.item_avatar);
    }

    @Override
    protected void performBind(Module module) {
        binding.setModule(module);
        Glide.with(context).load(module.getPicture()).into(picture);
    }


}
