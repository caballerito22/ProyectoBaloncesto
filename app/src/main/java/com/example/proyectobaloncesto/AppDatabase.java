package com.example.proyectobaloncesto;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


//esta es la clase para la base de datos, sirve para las opereciones que se realizan


//es una tabla de la clase jugador
@Database(entities = {Jugador.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //sirve vara que no hayan diferentes conexiones a la vez
    private static AppDatabase INSTANCE;
    //para obtener la insatancia
    public static AppDatabase getDatabase(Context context) {
        //si no existe la instancia la creo
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "db"
                    ).build();
        }
        return INSTANCE;
    }
    //devuelvo la interfaz DAO
    public abstract JugadorDAO getJugadorDAO();
}
