package com.example.proyectobaloncesto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.proyectobaloncesto.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayList<Jugador> listaJugadores;
    private ArrayAdapter<Jugador> adapter;
    private JugadoresViewModel model;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater);
        View view = binding.getRoot();

        listaJugadores = new ArrayList<>();
        adapter = new JugadorAdapter(
                getContext(),
                R.layout.jugador_list_item,
                listaJugadores
        );

        binding.ListViewJugadores.setAdapter(adapter);
        binding.ListViewJugadores.setOnItemClickListener((adapterView, view1, position, id) -> {
            Jugador jugador = adapter.getItem(position);
            Bundle navigation = new Bundle();
            navigation.putSerializable("Jugador", jugador);
            // Realizar la navegación al fragmento de detalles
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_jugadores_details, navigation);
        });

        model = new ViewModelProvider(this).get(JugadoresViewModel.class);
        model.getjugadores().observe(getViewLifecycleOwner(), jugadors -> {
            adapter.clear();
            adapter.addAll(jugadors);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        // Llamar a refresh() al crear la vista para obtener los datos automáticamente
        refresh();
    }

    void refresh() {
        model.reload();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Jugador> jugadores = JugadorAPI.buscar();

            getActivity().runOnUiThread(() -> {
                listaJugadores.clear(); // Asegúrate de limpiar la lista antes de agregar nuevos elementos
                for (Jugador p : jugadores) {
                    Log.d("XXX", p.toString());
                    listaJugadores.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });
    }



        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            int id = item.getItemId();

            if (id == R.id.action_refresh) {
                Toast.makeText(getContext(), "Click hecho", Toast.LENGTH_SHORT).show();
                Log.d("XXXMenu", "CLick");
                refresh();
            } else if (id == R.id.action_settings) {
                Log.d("XXX", "Settings Clicado");
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }

