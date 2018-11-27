package com.example.a41011561p.pokercontranadie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PlayingFragment extends Fragment {
    //https://opengameart.org/sites/default/files/card%20back%20red.png

    private TextView deckId;
    private String id;

    public PlayingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing, container, false);
        /*id = PokerContraNadieAPI*/

        deckId = view.findViewById(R.id.deckId);

        deckId.setText(id);

        return view;
    }
}
