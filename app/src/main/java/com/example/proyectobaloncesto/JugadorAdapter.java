package com.example.proyectobaloncesto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

//para adaptar la lista de Jugador y mostrarlos tipo lista
public class JugadorAdapter extends ArrayAdapter<Jugador> {
    public JugadorAdapter(@NonNull Context context, int resource, @NonNull List<Jugador> objects) {
        super(context, resource, objects);
    }

    //lo llamamaos simpre que mostremos un jugador dxe la lista
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //la posición del jugador que vamos a mostrar
            Jugador jugador = getItem(position);


            //PARA NO HACER NUEVAS VISTAS
            //si no existe la creamos
            if (convertView == null){

                //esto reutiliza la vista y no crea una cada vez que vamos a mostrar un jugador
                LayoutInflater inflater = LayoutInflater.from(getContext());

                convertView = inflater.inflate(R.layout.jugador_list_item, parent, false);
            }

            //obtenemos la imagen y el nombre del jugador mediante el findViewById
            TextView textoJugadorNombre = convertView.findViewById(R.id.textJugadorLista);
            ImageView imgImage = convertView.findViewById(R.id.imgJugadorLista);

            //le ponemos el nombre y la imagen (la imagen con Glide -> libreria para cargar imagnens)
            textoJugadorNombre.setText(jugador.getNombre());
            Glide.with(getContext()).load(jugador.getImagen()).into(imgImage);

            //devuelve la lista con el nombre y la imagen
            return convertView;
    }
}
