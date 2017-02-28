package com.melkir.libraries.modules;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.melkir.libraries.BR;
import com.melkir.libraries.R;
import com.melkir.libraries.activity.DetailActivity;
import com.melkir.libraries.model.Module;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ModulesFragment extends Fragment implements ModulesContract.View {
    public static final String TAG = ModulesFragment.class.getSimpleName();

    private ModulesContract.Presenter mPresenter;
    private ModulesAdapter mModulesAdapter;
    private RecyclerView mModulesView;
    private TextView mNoModulesView;

    public ModulesFragment() {
        // Requires empty public constructor
    }

    public static ModulesFragment newInstance() {
        return new ModulesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModulesAdapter = new ModulesAdapter(new ArrayList<Module>(0), mItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ModulesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_card, container, false);
        // Set up modules view
        mModulesView = (RecyclerView) root.findViewById(R.id.recycler_view);
        handleOrientationBehaviour(mModulesView);
        mModulesView.setAdapter(mModulesAdapter);
        mModulesView.setHasFixedSize(true);

        // Set up no modules view
        mNoModulesView = (TextView) root.findViewById(R.id.noModules);

        return root;
    }

    @Override
    public void showModules(List<Module> modules) {
        mModulesAdapter.replaceData(modules);

        mModulesView.setVisibility(View.VISIBLE);
        mNoModulesView.setVisibility(View.GONE);
    }

    @Override
    public void showNoModules() {
        mModulesView.setVisibility(View.GONE);
        mNoModulesView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showModuleDetailsUi(Module module) {
        final Context context = getContext();
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.MODULE, module);
        context.startActivity(intent);
    }

    @Override
    public void filter(ModuleFilterType requestType) {
        mModulesAdapter.getFilter().filter(requestType.getValue());
    }

    ModuleItemListener mItemListener = new ModuleItemListener() {
        @Override
        public void onModuleClick(Module clickedModule) {
            mPresenter.openModuleDetails(clickedModule);
        }
    };

    private void handleOrientationBehaviour(RecyclerView recyclerView) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
    }

    public static class ModulesAdapter extends RecyclerView.Adapter<ModulesViewHolder> implements Filterable {
        private List<Module> mModules;
        private ModulesFilter mItemFilter;
        private final ModuleItemListener mItemListener;

        public ModulesAdapter(List<Module> modules, ModuleItemListener itemListener) {
            setList(modules);
            mItemListener = itemListener;
            mItemFilter = new ModulesFilter(this, mModules);
        }

        @Override
        public ModulesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
            return new ModulesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ModulesViewHolder holder, int position) {
            holder.bind(mModules.get(position), mItemListener);
        }

        @Override
        public int getItemCount() {
            return mModules.size();
        }

        public void replaceData(List<Module> modules) {
            setList(modules);
            notifyDataSetChanged();
            mItemFilter.setOriginalList(modules);
        }

        public void setList(List<Module> modules) {
            mModules = checkNotNull(modules);
        }

        @Override
        public Filter getFilter() {
            return mItemFilter;
        }
    }

    private static class ModulesViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        private final ImageView picture;

        public ModulesViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            binding = DataBindingUtil.bind(itemView);
        }

        void bind(final Module module, final ModuleItemListener listener) {
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

    public interface ModuleItemListener {
        void onModuleClick(Module clickedModule);
    }
}
