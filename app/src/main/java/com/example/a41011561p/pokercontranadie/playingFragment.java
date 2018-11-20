package com.example.a41011561p.pokercontranadie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class playingFragment extends Fragment {
    //https://opengameart.org/sites/default/files/card%20back%20red.png

    public playingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playing, container, false);
    }
}
