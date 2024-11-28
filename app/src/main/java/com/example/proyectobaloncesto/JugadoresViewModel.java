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

//clase que se encarga del acceso a los datos de los jugadores en la base de datos local y carga los datos desde la API
public class JugadoresViewModel extends AndroidViewModel {
    //se necesita para acceder a la base de datos
    private final Application app;
    //se inicialica con la instancia de mi base de datos
    private final AppDatabase appDataBase;
    //se iniciliza con mi dao, para interactuar con la base de datos
    private final JugadorDAO jugadorDAO;

    public JugadoresViewModel(Application app){
        super(app);//constructor de la clase

        this.app=app;//referencia a la app
        this.appDataBase=AppDatabase.getDatabase(this.getApplication());//inicializa la base de datos
        this.jugadorDAO=appDataBase.getJugadorDAO();//DAO para acceder a los datos
    }

    //devuelve los jugadores en la base de datos, el gatJugadores para ver los cambios de los jugadores (devuelve el LIVE)
    public LiveData<List<Jugador>> getjugadores() {
        return jugadorDAO.getJugadores();
    }

    //recarga los datos (es el refrech del fristFragment), usa RefreshDataTask para coger los jugadores desde la API y actualizae la base de datos
    public void reload() {
        //crea la instancia
        RefreshDataTask task = new RefreshDataTask();
        //la ejecuta
        task.execute();
    }

    //actualiza los jugadores en segundo plano, usando la API para obtener los jugadores y luego actualizar la base de datos
    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            //llama a la api y otiene la lista de jugadores que guarda en un array
            JugadorAPI api = new JugadorAPI();
            ArrayList<Jugador> result;

            result = api.buscar();
            Log.d("XXXX",result.toString());
            //actualiza la base de datos
            jugadorDAO.deleteJugadores();
            jugadorDAO.addJugadores(result);

            return null;
        }
    }

}
