package com.melkir.libraries.modules;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melkir.libraries.R;
import com.melkir.libraries.data.Module;
import com.melkir.libraries.moduledetail.ModuleDetailActivity;
import com.melkir.libraries.modules.adapters.CardsAdapter;
import com.melkir.libraries.modules.adapters.SearchAdapter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class ModulesFragment extends Fragment implements ModulesContract.View {
    private static final String TAG = ModulesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.noModules)
    TextView mNoCardsView;

    private ModulesContract.Presenter mPresenter;
    private CardsAdapter mCardsAdapter;
    private SearchAdapter mSearchAdapter;

    public ModulesFragment() {
        // Requires empty public constructor
    }

    public static ModulesFragment newInstance() {
        return new ModulesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Module> modules = Collections.emptyList();
        mCardsAdapter = new CardsAdapter(modules, mItemListener);
        mSearchAdapter = new SearchAdapter(getContext(), modules, mItemListener);
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
        View root = inflater.inflate(R.layout.card_fragment, container, false);
        ButterKnife.bind(this, root);

        handleOrientationBehaviour(mRecyclerView);
        mRecyclerView.setAdapter(mCardsAdapter);
        mRecyclerView.setHasFixedSize(true);

        return root;
    }

    private void handleOrientationBehaviour(RecyclerView recyclerView) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
    }

    @Override
    public void showModules(List<Module> modules) {
        mCardsAdapter.replaceData(modules);
        mSearchAdapter.replaceData(modules);

        mRecyclerView.setVisibility(View.VISIBLE);
        mNoCardsView.setVisibility(View.GONE);
    }

    @Override
    public void showNoModules() {
        mRecyclerView.setVisibility(View.GONE);
        mNoCardsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showModuleDetailsUi(Module module) {
        final Context context = getContext();
        final Intent intent = new Intent(context, ModuleDetailActivity.class);
        intent.putExtra(ModuleDetailActivity.MODULE, module);
        context.startActivity(intent);
    }

    @Override
    public void filter(ModulesType requestType) {
        mCardsAdapter.filter(requestType.toString());
    }

    @Override
    public void filter(String requestTitle) {
        mSearchAdapter.filter(requestTitle);
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void showCardsViewUi() {
        mRecyclerView.setAdapter(mCardsAdapter);
    }

    @Override
    public void showSearchViewUi() {
        mRecyclerView.setAdapter(mSearchAdapter);
    }

    private final ModuleItemListener mItemListener = new ModuleItemListener() {
        @Override
        public void onModuleClick(Module clickedModule) {
            mPresenter.openModuleDetails(clickedModule);
        }
    };

    public interface ModuleItemListener {
        void onModuleClick(Module clickedModule);
    }

}
