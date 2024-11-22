package com.example.proyectobaloncesto;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JugadorAPI {
    private final String BASE_URL = "https://kbtxkoqwswyrxdonedfm.supabase.co/rest/v1/Jugadores%20Baloncesto?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtidHhrb3F3c3d5cnhkb25lZGZtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjMzOTksImV4cCI6MjA0NzIzOTM5OX0.-sAPPDL5oihEkmYqqHXAY74RMU8Lfe7LC7PSwZTcI1E";

    String getNombres (String nombre){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();

        String url = builtUri.toString();
        return doCall(url);
    }

    public static ArrayList<Jugador> buscar() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {

            String response = HttpUtils.get("https://kbtxkoqwswyrxdonedfm.supabase.co/rest/v1/Jugadores%20Baloncesto?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtidHhrb3F3c3d5cnhkb25lZGZtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjMzOTksImV4cCI6MjA0NzIzOTM5OX0.-sAPPDL5oihEkmYqqHXAY74RMU8Lfe7LC7PSwZTcI1E");
           // JSONObject jsonObject = new JSONObject(response);
            Log.d("XXX", "response");
            //JSONArray results = jsonObject.getJSONArray("Jugadores Baloncesto");
            JSONArray results = new JSONArray(response);


            for (int i = 0; i < results.length(); i++) {
                JSONObject jugadoorObj = results.getJSONObject(i);
                String name = jugadoorObj.getString("Nombre");

                Integer id = jugadoorObj.getInt("id");
                Integer puntos_totales = jugadoorObj.getInt("Puntos_totales");
                Integer minutos_jugados = jugadoorObj.getInt("Minutos_jugados");
                String fecha_nacimiento = jugadoorObj.getString("Fecha_nacimiento");
                String sprite = jugadoorObj.getString("Imagen");

                Jugador jugador = new Jugador(id,name,puntos_totales,minutos_jugados,fecha_nacimiento,sprite);
                jugadores.add(jugador);
            }
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }


    private String doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        }catch (IOException e){
            //para no tener que volver a retornar null
            throw new RuntimeException(e);
        }
    }

}
