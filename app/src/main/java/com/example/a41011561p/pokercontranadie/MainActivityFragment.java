package com.example.a41011561p.pokercontranadie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a41011561p.pokercontranadie.databinding.FragmentMainBinding;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private FragmentMainBinding binding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);
        View view = binding.getRoot();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.play:
                        Intent i = new Intent(getContext(), Playing.class);
                        startActivity(i);
                        break;
                    case R.id.history:
                        Intent ii = new Intent(getContext(), History.class);
                        startActivity(ii);
                        break;
                }
            }
        };

        binding.play.setOnClickListener(listener);
        binding.history.setOnClickListener(listener);

        return view;
    }
}
