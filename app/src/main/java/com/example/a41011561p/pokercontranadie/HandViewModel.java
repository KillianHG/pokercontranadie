package com.example.a41011561p.pokercontranadie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HandViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDatabase;
    private final HandDao cartasDao;
    private static final int PAGES = 10;
    private MutableLiveData<Boolean> loading;

    public HandViewModel(Application application) {
        super(application);

        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.cartasDao = appDatabase.getHandDao();

    }

    public LiveData<List<Hand>> getHand() {
        Log.d("DEBUG", "ENTRA");

        return cartasDao.getHand();
    }

    public MutableLiveData<Boolean> getLoading() {
        if(loading == null){
            loading = new MutableLiveData<>();
        }
        return loading;
    }

    /*public void newGame() {
        NewGameDataTask task = new NewGameDataTask();
        task.execute();
    }

    private class NewGameDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setValue(true);
        }
            @Override
        protected String doInBackground(Void... voids) {
            String result;
            PokerContraNadieAPI api = new PokerContraNadieAPI();
            result = api.newDeckID();
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loading.setValue(false);
        }
    }*/
}

