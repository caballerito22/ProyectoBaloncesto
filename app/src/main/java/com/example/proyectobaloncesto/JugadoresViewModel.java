package com.example.proyectobaloncesto;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class JugadoresViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDataBase;
    private final JugadorDAO jugadorDAO;

    public JugadoresViewModel(Application app){
        super(app);

        this.app=app;
        this.appDataBase=AppDatabase.getDatabase(this.getApplication());
        this.jugadorDAO=appDataBase.getJugadorDAO();
    }

    public LiveData<List<Jugador>> getjugadores() {
        return jugadorDAO.getJugadores();
    }

    public void reload() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );

            JugadorAPI api = new JugadorAPI();
            ArrayList<Jugador> result;

            result = api.buscar();
            Log.d("XXXX",result.toString());
            jugadorDAO.deleteJugadores();
            jugadorDAO.addJugadores(result);

            return null;
        }
    }

}
