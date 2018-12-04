package com.example.a41011561p.pokercontranadie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<String> items;
    //private HandAdapter adapter;
    private ArrayAdapter<String> adapter;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ListView lvHistory = view.findViewById(R.id.lvHistory);

        String[] data = {
                "1",
                "2",
                "3",
                "4",
                "5",
                "7",
                "8"
        };

        items = new ArrayList<>(Arrays.asList(data));

        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_history_row,
                items
        );
        lvHistory.setAdapter(adapter);

        return view;
    }
}