package com.example.listapelicula;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Listado extends AppCompatActivity {
    ArrayList<Pelicula> lista;
    RecyclerView rvpelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado);

        lista = new ArrayList<>();
        rvpelicula = findViewById(R.id.lstpelicula);
        rvpelicula.setLayoutManager(new LinearLayoutManager(this));
        Intent i = getIntent();
        lista = i.getParcelableArrayListExtra("pelis");

        peli peliculaAdapter = new peli(lista);
        rvpelicula.setAdapter(peliculaAdapter);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        peli peliculaAdapter;

        switch (id) {
            case R.id.action_ordenar_genero:
                Collections.sort(lista, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getGenero().compareTo(o2.getGenero()) < 0) {
                            return -1;
                        }
                        if (o1.getGenero().compareTo(o2.getGenero()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                peliculaAdapter = new peli(lista);
                rvpelicula.setAdapter(peliculaAdapter);
                break;
            case R.id.action_orderar_nombre:
                Collections.sort(lista, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
                            return -1;
                        }
                        if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                peliculaAdapter = new peli(lista);
                rvpelicula.setAdapter(peliculaAdapter);
                break;
            case R.id.action_invertir:
                Collections.reverse(lista);
                peliculaAdapter = new peli(lista);
                rvpelicula.setAdapter(peliculaAdapter);
                break;
            case R.id.action_eliminar_primero:
                AlertDialog.Builder builder = new AlertDialog.Builder(Listado.this);
                builder.setMessage("Desea eliminar una pelicula?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                lista.remove(0);
                                peli peliculaAdapter = new peli(lista);
                                rvpelicula.setAdapter(peliculaAdapter);
                                Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alerta = builder.create();
                alerta.show();
                break;
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                i.putParcelableArrayListExtra("pelis", lista);
                setResult(Activity.RESULT_OK, i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
