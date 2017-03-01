package com.melkir.libraries.modules.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.melkir.libraries.BR;
import com.melkir.libraries.R;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesFragment;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> implements Filterable {
    private static final String TAG = CardsAdapter.class.getSimpleName();

    private List<Module> mModules;
    private CardsFilter mItemFilter;
    private final ModulesFragment.ModuleItemListener mItemListener;

    public CardsAdapter(List<Module> modules, ModulesFragment.ModuleItemListener itemListener) {
        setList(modules);
        mItemListener = itemListener;
        mItemFilter = new CardsFilter(this, mModules);
    }

    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {
        holder.bind(mModules.get(position), mItemListener);
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public void replaceData(List<Module> modules) {
        setList(modules);
//        mItemFilter.notifyDataChanged(modules);
        notifyDataSetChanged();
    }

    public void setList(List<Module> modules) {
        mModules = checkNotNull(modules);
    }

    @Override
    public Filter getFilter() {
        return mItemFilter;
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        private final ImageView picture;

        CardsViewHolder(View itemView) {
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

