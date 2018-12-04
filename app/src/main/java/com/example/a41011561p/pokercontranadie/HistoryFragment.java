package com.example.a41011561p.pokercontranadie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<Hand> items;
    private HandAdapter adapter;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ListView lvHistory = view.findViewById(R.id.lvHistory);

        items = new ArrayList<>();

        adapter = new HandAdapter(getContext(), R.layout.lv_history_row, items);
        lvHistory.setAdapter(adapter);

        return view;
    }
}