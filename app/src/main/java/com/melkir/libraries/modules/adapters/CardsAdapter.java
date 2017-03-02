package com.melkir.libraries.modules.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.melkir.libraries.data.Module;
import com.melkir.libraries.databinding.ItemCardBinding;
import com.melkir.libraries.modules.ModulesFragment;
import com.melkir.libraries.modules.ModulesType;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {
    private static final String TAG = CardsAdapter.class.getSimpleName();

    private List<Module> mDefaultList;
    private List<Module> mFilteredList;
    private final ModulesFragment.ModuleItemListener mItemListener;

    public CardsAdapter(List<Module> modules, ModulesFragment.ModuleItemListener itemListener) {
        setList(modules);
        mItemListener = itemListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemCardBinding binding = ItemCardBinding.inflate(inflater, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.bind(mFilteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public void replaceData(List<Module> modules) {
        Log.d(TAG, "replaceData");
        mDefaultList = checkNotNull(modules);
        if (mFilteredList.size() == 0) mFilteredList = mDefaultList;
        notifyDataSetChanged();
    }

    public void setList(List<Module> modules) {
        mDefaultList = checkNotNull(modules);
        mFilteredList = checkNotNull(modules);
    }

    public void filter(String query) {
        mFilteredList = new ArrayList<>();
        if (query.equals(ModulesType.ALL_CATEGORIES.toString())) {
            mFilteredList.addAll(mDefaultList);
        } else {
            for (final Module module : mDefaultList) {
                if (this.contains(module.getCategories(), query)) {
                    mFilteredList.add(module);
                }
            }
        }
        notifyDataSetChanged();
    }

    private <T> boolean contains(final T[] array, final T v) {
        if (v == null) {
            for (final T e : array) if (e == null) return true;
        } else {
            for (final T e : array) if (e == v || v.equals(e)) return true;
        }
        return false;
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private final ItemCardBinding binding;

        CardViewHolder(ItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final Module module) {
            ImageView picture = binding.cardImage;
            Glide.with(picture.getContext()).load(module.getPicture()).into(picture);
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
