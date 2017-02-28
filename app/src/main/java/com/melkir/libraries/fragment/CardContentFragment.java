package com.melkir.libraries.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melkir.libraries.R;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesContract;
import com.melkir.libraries.modules.ModulesPresenter;
import com.melkir.libraries.data.ModulesRepository;
import com.melkir.libraries.view.CardAdapter;
import com.melkir.libraries.view.SearchAdapter;

import java.util.List;

/**
 * Provides UI for the view with Cards.
 */
public class CardContentFragment extends Fragment implements ModulesContract.View {

    private CardAdapter mCardAdapter;
    private SearchAdapter mSearchAdapter;
    private RecyclerView mRecyclerView;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        activity = getActivity();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        int orientation = activity.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        }

        ModulesPresenter presenter = new ModulesPresenter(new ModulesRepository(activity), this);

        presenter.start();

        return rootView;
    }

    public void cardFilter(String constraint) {
        this.mCardAdapter.getFilter().filter(constraint);
    }

    public void searchFilter(String constraint) {
        this.mSearchAdapter.getFilter().filter(constraint);
        mRecyclerView.scrollToPosition(0);
    }

    public void setCardView() {
        mRecyclerView.setAdapter(mCardAdapter);
    }

    public void setSearchView() {
        mRecyclerView.setAdapter(mSearchAdapter);
    }

    @Override
    public void showModules(List<Module> modules) {
        // Initialize card adapter
        mCardAdapter = new CardAdapter(activity, modules);

        // Initialize search adapter
        mSearchAdapter = new SearchAdapter(activity, modules);
        mSearchAdapter.edit().replaceAll(modules).commit();

        // Set card adapter by default
        mRecyclerView.setAdapter(mCardAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void setPresenter(ModulesContract.Presenter presenter) {
    }
}
