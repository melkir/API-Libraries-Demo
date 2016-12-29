package com.melkir.libraries.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melkir.libraries.R;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.view.CardAdapter;
import com.melkir.libraries.view.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides UI for the view with Cards.
 */
public class CardContentFragment extends Fragment {

    private CardAdapter mCardAdapter;
    private SearchAdapter mSearchAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        final Activity activity = getActivity();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        int orientation = activity.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        }

        // Initialize list of module
        List<Module> modules = initModules(activity);

        // Initialize card adapter
        mCardAdapter = new CardAdapter(activity, modules);

        // Initialize search adapter
        mSearchAdapter = new SearchAdapter(activity, modules);
        mSearchAdapter.edit().replaceAll(modules).commit();

        // Set card adapter by default
        mRecyclerView.setAdapter(mCardAdapter);
        mRecyclerView.setHasFixedSize(true);

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

    private List<Module> initModules(Context context) {
        List<Module> modules = new ArrayList<>();
        Resources resources = context.getResources();
        final String[] mModulesTitle = resources.getStringArray(R.array.modules_title);
        final String[] mModulesDesc = resources.getStringArray(R.array.modules_desc);
        final String[] mModulesCategory = resources.getStringArray(R.array.modules_category);
        final String[] mModulesLink = resources.getStringArray(R.array.modules_link);
        final String[] mModulesAction = resources.getStringArray(R.array.modules_action);
        final TypedArray a = resources.obtainTypedArray(R.array.modules_picture);
        for (int i = 0; i < mModulesTitle.length; ++i) {
            String[] categories = mModulesCategory[i].split(";");
            Module module = new Module(i, mModulesTitle[i], mModulesDesc[i], mModulesLink[i],
                    mModulesAction[i], categories, a.getResourceId(i, R.drawable.card_demo));
            modules.add(module);
        }
        a.recycle();
        return modules;
    }

}
