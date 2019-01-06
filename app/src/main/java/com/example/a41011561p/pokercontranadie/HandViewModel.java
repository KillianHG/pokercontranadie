package com.example.a41011561p.pokercontranadie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

public class HandViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDatabase;
    private final HistoryDBDao historyDBDao;

    public HandViewModel(Application application) {
        super(application);

        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.historyDBDao = appDatabase.getHistoryDao();

    }

    public LiveData<List<HistoryDB>> getHistory() {
        Log.d("DEBUG", "ENTRA");
        return historyDBDao.getHistory();
    }

    public void addHand(HistoryDB hand) {
        AddHandDataTask task = new AddHandDataTask(hand);
        task.execute();
    }

    private class AddHandDataTask extends AsyncTask<Void, Void, HistoryDB> {
        private HistoryDB h;
        public AddHandDataTask(HistoryDB hand)
        {
            h = hand;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected HistoryDB doInBackground(Void... voids) {
            historyDBDao.addHistory(h);
            return h;
        }

    }
}

