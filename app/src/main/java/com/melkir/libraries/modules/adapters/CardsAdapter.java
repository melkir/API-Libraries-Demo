package com.melkir.libraries.modules.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.melkir.libraries.BR;
import com.melkir.libraries.R;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesFragment;
import com.melkir.libraries.modules.ModulesType;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.Holder> {
    private static final String TAG = CardsAdapter.class.getSimpleName();

    private List<Module> mDefaultList;
    private List<Module> mFilteredList;
    private final ModulesFragment.ModuleItemListener mItemListener;

    public CardsAdapter(List<Module> modules, ModulesFragment.ModuleItemListener itemListener) {
        setList(modules);
        mItemListener = itemListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mFilteredList.get(position), mItemListener);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public void replaceData(List<Module> modules) {
        setList(modules);
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
            for (final Module module: mDefaultList) {
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

    class Holder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        private final ImageView picture;

        Holder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            binding = DataBindingUtil.bind(itemView);
        }

        void bind(final Module module, final ModulesFragment.ModuleItemListener listener) {
            Glide.with(picture.getContext()).load(module.getPicture()).into(picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onModuleClick(module);
                }
            });
            binding.setVariable(BR.module, module);
            binding.executePendingBindings();
        }
    }
}
