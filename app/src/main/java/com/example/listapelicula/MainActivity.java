package com.example.listapelicula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

        EditText nombre,director;
    RadioButton español, ingles;
    RadioGroup idioma;
    Button guardar, cancelar;
    Spinner genero;
    ArrayList<Pelicula> peliculas = new ArrayList<>();
    String generopelicula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = findViewById(R.id.edtnombre);
        director = findViewById(R.id.edtdirector);
        español = findViewById(R.id.rbespañol);
        ingles = findViewById(R.id.rbingles);
        idioma = findViewById(R.id.rgidioma);
        guardar = findViewById(R.id.btguardar);
        cancelar = findViewById(R.id.btcancelar);
        genero = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.spinner, R.layout.support_simple_spinner_dropdown_item);
        genero.setAdapter(adapter);
        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generopelicula = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btguardar:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Desea agregar esta pelicula?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Idioma;
                        switch (idioma.getCheckedRadioButtonId()) {
                            case R.id.rbespañol:
                                Idioma = ("Español");
                                break;
                            case R.id.rbingles:
                                Idioma = ("Ingles");
                                break;
                            default:
                                Idioma = ("Español");
                                break;
                        }
                        Pelicula pelicula = new Pelicula(nombre.getText().toString(), director.getText().toString(), Idioma, generopelicula);
                        peliculas.add(pelicula);
                        nombre.setText("");
                        director.setText("");
                        Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                break;
            case R.id.btcancelar:
                nombre.setText("");
                director.setText("");
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case R.id.mayuscula:
                nombre.setText(nombre.getText().toString().toUpperCase());
                director.setText(director.getText().toString().toUpperCase());
                break;
            case R.id.listados:
                Intent i = new Intent(this, Listado.class);
                i.putParcelableArrayListExtra("pelis", peliculas);
                startActivityForResult(i, 5);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            peliculas = data.getParcelableArrayListExtra("pelis");

        }

    }
}
