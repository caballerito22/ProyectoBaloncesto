package com.example.proyectobaloncesto;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;


//sirve para manejarse con la base de datos
//cada metodo hace una cosa sobre la tabla jugador
@Dao
public interface JugadorDAO {
    //obtiene los jugadores y se actualizan automaticamente si hay cambios
    @Query("select * from jugador")
    LiveData<List<Jugador>> getJugadores();

    @Insert
    void addJugador(Jugador jugador);

    @Insert
    void addJugadores(List<Jugador> jugadores);

    @Delete
    void deleteJugador(Jugador jugador);

    @Query("DELETE FROM jugador")
    void deleteJugadores();

}
