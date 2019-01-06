package com.example.a41011561p.pokercontranadie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a41011561p.pokercontranadie.databinding.FragmentDetailsBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private View view;
    private FragmentDetailsBinding binding;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater);
        view = binding.getRoot();

        Intent i = getActivity().getIntent();

        if (i != null) {
            HistoryDB hand = (HistoryDB) i.getSerializableExtra("hand");

            if (hand != null) {
                updateUi(hand);
            }
        }


        return view;
    }

    private void updateUi(HistoryDB hand) {
        Log.d("MOVIE", hand.toString());

        Glide.with(getContext()).load(
                hand.getCard1()
        ).into(binding.card1);
        Glide.with(getContext()).load(
                hand.getCard2()
        ).into(binding.card2);
        Glide.with(getContext()).load(
                hand.getCard3()
        ).into(binding.card3);
        Glide.with(getContext()).load(
                hand.getCard4()
        ).into(binding.card4);
        Glide.with(getContext()).load(
                hand.getCard5()
        ).into(binding.card5);

    }


}
