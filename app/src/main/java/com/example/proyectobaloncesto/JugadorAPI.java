package com.example.proyectobaloncesto;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


//CLASE PARA USAR NUESTRA API
public class JugadorAPI {
    //nuestra URL donde tenemos creada la tabla
    private final String BASE_URL = "https://kbtxkoqwswyrxdonedfm.supabase.co/rest/v1/Jugadores%20Baloncesto?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtidHhrb3F3c3d5cnhkb25lZGZtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjMzOTksImV4cCI6MjA0NzIzOTM5OX0.-sAPPDL5oihEkmYqqHXAY74RMU8Lfe7LC7PSwZTcI1E";


    //los datos obtenidos, los mete en un Array
    public static ArrayList<Jugador> buscar() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {

            //hace la solicitud y obtiene los datos en formato JSON
            String response = HttpUtils.get("https://kbtxkoqwswyrxdonedfm.supabase.co/rest/v1/Jugadores%20Baloncesto?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtidHhrb3F3c3d5cnhkb25lZGZtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjMzOTksImV4cCI6MjA0NzIzOTM5OX0.-sAPPDL5oihEkmYqqHXAY74RMU8Lfe7LC7PSwZTcI1E");
            Log.d("XXX", "response");
            JSONArray results = new JSONArray(response);


            //mediante la respuesta, coje losdatos de cada jugador y crea los objetos
            for (int i = 0; i < results.length(); i++) {
                JSONObject jugadoorObj = results.getJSONObject(i);
                String name = jugadoorObj.getString("Nombre");

                Integer id = jugadoorObj.getInt("id");
                Integer puntos_totales = jugadoorObj.getInt("Puntos_totales");
                Integer minutos_jugados = jugadoorObj.getInt("Minutos_jugados");
                String fecha_nacimiento = jugadoorObj.getString("Fecha_nacimiento");
                String sprite = jugadoorObj.getString("Imagen");

                //los añade a la lista
                Jugador jugador = new Jugador(id,name,puntos_totales,minutos_jugados,fecha_nacimiento,sprite);
                jugadores.add(jugador);
            }
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }

}
