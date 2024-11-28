package com.example.proyectobaloncesto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyectobaloncesto.databinding.FragmentJugadoresDetailsBinding;

/*
*
 * A simple {@link Fragment} subclass.
 * Use the {@link jugadores_details#newInstance} factory method to
 * create an instance of this fragment.
*/

//esta clase sirve para mostrar los detalles en el movil de la derecha
public class jugadores_details extends Fragment {

    //lo dicho anteriormente
    public jugadores_details() {}

    //se va a usar par los detelles -> el  xml(de detellas) y la clase (de detalles)
    private FragmentJugadoresDetailsBinding binding;

    //se generan las vistas a partir del XML
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentJugadoresDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //se usa cuando la vista ya ha sido creada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //se obtienen los argumentos(detalles del jugador)
        Bundle args = getArguments();

        //si los argumentos no son nulos se pasan al metodo de mostrar (showJugador)
        if (args != null) {
            Jugador jugador = (Jugador) args.getSerializable("Jugador");
            if (jugador != null) {
                Log.d("JugadorDetail", jugador.toString());
                showJugador(jugador);
            }
        }
    }

    //codigo facil, actualiza el jugador con sus detalles
    private void showJugador(Jugador jugador) {
        Log.d("Jugador", jugador.toString());
        binding.txtNombreDetail.setText("Nombre: " + jugador.getNombre());
        binding.txtPuntosDetail.setText("Puntos anotados: "+jugador.getPuntos_totales());
        binding.txtNacimientoDetail.setText("Fecha de Nacimiento: " + jugador.getFecha_nacimiento().toString());
        binding.txtMinutosDetail.setText("Minutos jugados: "+jugador.getMinutos_jugados());
        binding.txtIdDetail.setText("Su id: "+jugador.getId());

        //como he dicho antes, la imagen igual pero en los detalles
        Glide.with(requireContext()).load(jugador.getImagen()).into(binding.imgJugadorDetail);
    }
}


    /*// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public jugadores_details() {
        // Required empty public constructor
    }

    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment jugadores_details.
     *//*
    // TODO: Rename and change types and number of parameters
    public static jugadores_details newInstance(String param1, String param2) {
        jugadores_details fragment = new jugadores_details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jugadores_details, container, false);
    }*/
