package com.melkir.libraries.cards;

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
import com.melkir.libraries.activity.DetailActivity;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesContract;
import com.melkir.libraries.modules.ModulesType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class CardsFragment extends Fragment implements ModulesContract.View {
    private static final String TAG = CardsFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mCardsView;
    @BindView(R.id.noModules) TextView mNoCardsView;

    private ModulesContract.Presenter mPresenter;
    private CardsAdapter mCardsAdapter;

    public CardsFragment() {
        // Requires empty public constructor
    }

    public static CardsFragment newInstance() {
        return new CardsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardsAdapter = new CardsAdapter(new ArrayList<Module>(0), mItemListener);
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
        ButterKnife.bind(this, root);

        handleOrientationBehaviour(mCardsView);
        mCardsView.setAdapter(mCardsAdapter);
        mCardsView.setHasFixedSize(true);

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

//        mCardsView.setVisibility(View.VISIBLE);
//        mNoCardsView.setVisibility(View.GONE);
    }

    @Override
    public void showNoModules() {
//        mCardsView.setVisibility(View.GONE);
//        mNoCardsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showModuleDetailsUi(Module module) {
        final Context context = getContext();
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.MODULE, module);
        context.startActivity(intent);
    }

    @Override
    public void filter(ModulesType requestType) {
        mCardsAdapter.getFilter().filter(requestType.getValue());
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
