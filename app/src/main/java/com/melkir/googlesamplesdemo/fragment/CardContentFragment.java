package com.melkir.googlesamplesdemo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.model.Module;
import com.melkir.googlesamplesdemo.view.CardAdapter;
import com.melkir.googlesamplesdemo.view.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides UI for the view with Cards.
 */
public class CardContentFragment extends Fragment {

    private CardAdapter mCardAdapter;
    private SearchAdapter mSearchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Module> modules = initModules(getActivity());
        this.mCardAdapter = new CardAdapter(modules);
//        this.mSearchAdapter = new SearchAdapter(modules);

        recyclerView.setAdapter(this.mCardAdapter);
//        recyclerView.setAdapter(this.mSearchAdapter);
        recyclerView.setHasFixedSize(true);

        return rootView;
    }

    public void cardFilter(String constraint) {
        this.mCardAdapter.getFilter().filter(constraint);
    }

    public void searchFilter(String constraint) {
        this.mSearchAdapter.getFilter().filter(constraint);
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
            Module module = new Module(mModulesTitle[i], mModulesDesc[i], mModulesLink[i],
                    mModulesAction[i], categories, a.getResourceId(i, R.drawable.card_demo));
            modules.add(module);
        }
        a.recycle();
        return modules;
    }

}
