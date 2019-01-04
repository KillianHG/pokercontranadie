package com.example.a41011561p.pokercontranadie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a41011561p.pokercontranadie.databinding.FragmentHistoryBinding;

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
    private FragmentHistoryBinding binding;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater);
        View view = binding.getRoot();

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
                R.id.gameScore,
                items
        );
        binding.lvHistory.setAdapter(adapter);

        return view;
    }
}