package com.melkir.googlesamplesdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.view.CardAdapter;

/**
 * Provides UI for the view with Cards.
 */
public class CardContentFragment extends Fragment {

    private CardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.adapter = new CardAdapter(getActivity());

        recyclerView.setAdapter(this.adapter);
        recyclerView.setHasFixedSize(true);

        return rootView;
    }

    public void filter(String constraint) {
        this.adapter.getFilter().filter(constraint);
    }

}
