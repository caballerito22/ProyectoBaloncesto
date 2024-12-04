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


//es como el main
public class FirstFragment extends Fragment {

    //para ir a los enementos de la interfaz
    private FragmentFirstBinding binding;
    //la lista de los jugadores
    private ArrayList<Jugador> listaJugadores;
    //para conectar la lista -intermediario-
    private ArrayAdapter<Jugador> adapter;
    //para manejar los datos
    private JugadoresViewModel model;


    //creo la vista del fragmento
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater);
        View view = binding.getRoot();

        //crea la lista de jugadores
        listaJugadores = new ArrayList<>();
        //el adapter para enlazar los jugadores con la lista (listView)
        adapter = new JugadorAdapter(
                getContext(),
                R.layout.jugador_list_item,
                listaJugadores
        );

        //asigno el adapter al listView
        binding.ListViewJugadores.setAdapter(adapter);
        //esto sirve para que cuando selecciono un jugador en el listView me lleve a los detalles
        binding.ListViewJugadores.setOnItemClickListener((adapterView, view1, position, id) -> {
            Jugador jugador = adapter.getItem(position);
            //aqui van los datos del jugador
            Bundle navigation = new Bundle();
            navigation.putSerializable("Jugador", jugador);
            //con esto voy al fragmento de detalles (jugadores_details)
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_jugadores_details, navigation);
        });

        //tengo la clase JugadoresViewModel
        model = new ViewModelProvider(this).get(JugadoresViewModel.class);
        //si la lista de jugadores cambia, el adapter se actualiza
        model.getjugadores().observe(getViewLifecycleOwner(), jugadors -> {
            adapter.clear();
            adapter.addAll(jugadors);
        });

        //para que se muestre la vista
        return view;
    }


    //muestro el menu de opciones
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }


    //actualizo mrdiante el metodo que tengo en el viewModel
    private void refresh(){
        model.reload();
    }



    //sencillo, si toco refresh me lleva, si no settings
        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            int id = item.getItemId();

            if (id == R.id.action_refresh) {
                Toast.makeText(getContext(), "Refresh clikado", Toast.LENGTH_SHORT).show();
                Log.d("XXXMenu", "CLick");
                refresh();
            } else if (id == R.id.action_settings) {
                Log.d("XXX", "Settings Clikado");
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        //no he tocado nada
        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }

