package com.example.proyectobaloncesto;

import android.net.Uri;

import java.io.IOException;

public class JugadorAPI {
    private final String BASE_URL = "https://kbtxkoqwswyrxdonedfm.supabase.co/rest/v1/Jugadores%20Baloncesto?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtidHhrb3F3c3d5cnhkb25lZGZtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjMzOTksImV4cCI6MjA0NzIzOTM5OX0.-sAPPDL5oihEkmYqqHXAY74RMU8Lfe7LC7PSwZTcI1E";

    String getNombres (String nombre){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("equipo_actual")
                .build();

        String url = builtUri.toString();
        return doCall(url);


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
