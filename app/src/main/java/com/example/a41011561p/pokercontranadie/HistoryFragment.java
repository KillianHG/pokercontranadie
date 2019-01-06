package com.example.a41011561p.pokercontranadie;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.arch.lifecycle.ViewModelProviders;

import com.example.a41011561p.pokercontranadie.databinding.FragmentHistoryBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<HistoryDB> items;
    private HandAdapter adapter;

    private HandViewModel model;
    private FragmentHistoryBinding binding;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater);
        View view = binding.getRoot();

        items = new ArrayList<>();
        adapter = new HandAdapter(
                getContext(),
                R.layout.lv_history_row,
                items
        );
        binding.lvHistory.setAdapter(adapter);

        binding.lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HistoryDB hand = (HistoryDB) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("hand", hand);

                startActivity(intent);
            }
        });


        model = ViewModelProviders.of(this).get(HandViewModel.class);
        model.getHistory().observe(this, new Observer<List<HistoryDB>>() {
            @Override
            public void onChanged(@Nullable List<HistoryDB> historyDBS) {
                adapter.addAll(historyDBS);
            }
        });
        return view;
    }
}