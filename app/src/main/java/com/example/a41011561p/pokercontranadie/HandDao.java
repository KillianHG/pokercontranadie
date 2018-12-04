package com.example.a41011561p.pokercontranadie;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HandDao {
    @Query("select * from Hand")
    LiveData<List<Hand>> getHand();

    @Insert
    void addHand(Hand hand);

    @Insert
    void addHands(List<Hand> hand);


    @Delete
    void deleteHand(Hand hand);

    @Query("DELETE FROM Hand")
    void deleteHand();
}
