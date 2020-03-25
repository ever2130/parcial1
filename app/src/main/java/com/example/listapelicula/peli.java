package com.example.listapelicula;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class peli extends RecyclerView.Adapter<peli.ViewHolderPelicula> {

    private ArrayList<Pelicula> listaPeliculas;

    public peli(ArrayList<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public peli.ViewHolderPelicula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista, null, false);
        return new ViewHolderPelicula(view);
    }



    @Override
    public void onBindViewHolder(@NonNull peli.ViewHolderPelicula holder, int position) {
        holder.nombre.setText(listaPeliculas.get(position).getNombre());
        holder.director.setText(listaPeliculas.get(position).getDirector());
        holder.genero.setText(listaPeliculas.get(position).getGenero());
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public class ViewHolderPelicula extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView nombre, director, genero;

        public ViewHolderPelicula(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtnombre);
            director = itemView.findViewById(R.id.txtdirector);
            genero = itemView.findViewById(R.id.txtgenero);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
