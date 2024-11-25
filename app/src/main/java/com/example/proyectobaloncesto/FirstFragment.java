package com.example.proyectobaloncesto;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.proyectobaloncesto.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList <Jugador> listaJugadores;
    ArrayAdapter <Jugador> adapter;

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
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Jugador> fetchedJugadores  = JugadorAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Jugador p : fetchedJugadores) {
                    Log.d("XXX", p.toString());
                    listaJugadores.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });

      /*  refresh();*/
        adapter = new JugadorAdapter(getContext(),
                R.layout.jugador_list_item, listaJugadores);
        binding.ListViewJugadores.setAdapter(adapter);


        binding.ListViewJugadores.setOnItemClickListener((adapter, fragment, i, l) -> {
                    Jugador jugador = (Jugador) adapter.getItemAtPosition(i);
                    Bundle args = new Bundle();
                    args.putSerializable("Jugador", jugador);

                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_jugadores_details, args);
                }
        );

    }

   /* @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        //model.reload();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Jugador> fetchedJugadores  = JugadorAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Jugador p : fetchedJugadores) {
                    Log.d("XXX", p.toString());
                    listaJugadores.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });


        binding.ListViewJugadores.setOnItemClickListener((adapterView, fragment, i, l) -> {
            Jugador jugador = adapter.getItem(i);
            Bundle args = new Bundle();
            args.putSerializable("Jugador", jugador);
            Log.d("XXX", jugador.toString());
        });
    }

        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}