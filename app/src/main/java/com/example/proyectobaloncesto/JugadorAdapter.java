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

public class JugadorAdapter extends ArrayAdapter<Jugador> {
    public JugadorAdapter(@NonNull Context context, int resource, @NonNull List<Jugador> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Jugador jugador = getItem(position);


            //PARA NO HACER NUEVAS VISTAS
            if (convertView == null){

                LayoutInflater inflater = LayoutInflater.from(getContext());

                convertView = inflater.inflate(R.layout.jugador_list_item, parent, false);
            }

            TextView textoJugadorNombre = convertView.findViewById(R.id.textJugadorLista);
            ImageView imgImage = convertView.findViewById(R.id.imgJugadorLista);

            textoJugadorNombre.setText(jugador.getNombre());
            Glide.with(getContext()).load(jugador.getImagen()).into(imgImage);

            return convertView;
    }
}
