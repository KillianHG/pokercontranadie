package com.example.a41011561p.pokercontranadie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<HistoryDB> selected = new MutableLiveData<HistoryDB>();

    public void select(HistoryDB hand) {
        selected.setValue(hand);
    }

    public LiveData<HistoryDB> getSelected() {
        return selected;
    }
}
