package com.example.a41011561p.pokercontranadie;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface HistoryDBDao {
    @Query("select * from HistoryDB")
    LiveData<List<HistoryDB>> getHistory();

    @Insert
    void addHistory(HistoryDB hand);

    @Insert
    void addHistorys(List<HistoryDB> hand);

    @Delete
    void deleteHistory(HistoryDB hand);

    @Query("DELETE FROM HistoryDB")
    void deleteHistory();
}
