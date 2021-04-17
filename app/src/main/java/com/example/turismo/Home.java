package com.example.turismo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<Turismo> listadeDatos=new ArrayList<>();
    RecyclerView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listado=findViewById(R.id.listado);
        listado.setHasFixedSize(true);
        listado.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        
        crearlistado(); // metodo que se crea para cargar los datos
        AdaptadorLista adaptador=new AdaptadorLista(listadeDatos);
        listado.setAdapter(adaptador);
    }

    private void crearlistado() {

        listadeDatos.add(new Turismo(
                "Actividad:Senderismo",
                "Edad: 12-70 Años",
                "Hora:8am-3pm",
                getString(R.string.descripcion1),
                R.drawable.senderismo1


        ));
        listadeDatos.add(new Turismo(
                "Actividad:Cristo Rey",
                "Edad: 12-65 Años",
                "Hora:8am-3pm",
                getString(R.string.descripcion2),
                R.drawable.critorey


        ));
        listadeDatos.add(new Turismo(
                "Actividad:Torrentismo",
                "Edad: 15-50 Años",
                "Hora:8am-12pm",
                getString(R.string.descripcion3),
                R.drawable.torrentismo


        ));



    }
}