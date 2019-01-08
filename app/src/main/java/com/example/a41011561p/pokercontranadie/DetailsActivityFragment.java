package com.example.a41011561p.pokercontranadie;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
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

        SharedViewModel sharedModel = ViewModelProviders.of(
                getActivity()
        ).get(SharedViewModel.class);
        sharedModel.getSelected().observe(this, new Observer<HistoryDB>() {
            @Override
            public void onChanged(@Nullable HistoryDB hand) {
                updateUi(hand);
            }
        });

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

        String[] discards = hand.getDiscardList().split(" ");

        if (discards.length>0) {
            Glide.with(getContext()).load(
                    discards[0]
            ).into(binding.discard1);
            if (discards.length>1) {
                Glide.with(getContext()).load(
                        discards[1]
                ).into(binding.discard2);
                if (discards.length>2) {
                    Glide.with(getContext()).load(
                            discards[2]
                    ).into(binding.discard3);
                    if (discards.length>3) {
                        Glide.with(getContext()).load(
                                discards[3]
                        ).into(binding.discard4);
                        if (discards.length>4) {
                            Glide.with(getContext()).load(
                                    discards[4]
                            ).into(binding.discard5);
                        }
                    }
                }
            }
        }
    }
}
