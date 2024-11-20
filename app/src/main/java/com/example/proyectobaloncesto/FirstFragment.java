package com.example.proyectobaloncesto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectobaloncesto.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList <String> listaJugadores;
    ArrayAdapter <String> adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

               binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaJugadores = new ArrayList<>();
        listaJugadores.add("Lebron James");
        listaJugadores.add("Kareem Abdul-Jabbar");
        listaJugadores.add("Karl Malone");

        adapter = new ArrayAdapter<>(getContext(),
                R.layout.jugador_list_item,
                R.id.textJugadorLista,
                listaJugadores);
        binding.ListViewJugadores.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}