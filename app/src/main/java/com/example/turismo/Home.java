package com.example.turismo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Locale;

public class Home extends AppCompatActivity {

    // Atributos

    ArrayList<Turismo> listadeDatos=new ArrayList<>();
    RecyclerView listado;

    FirebaseFirestore baseDatos = FirebaseFirestore.getInstance();
// Metodos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listado=findViewById(R.id.listado);
        listado.setHasFixedSize(true);
        listado.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        
        crearlistado(); // metodo que se crea para cargar los datos

    }

    private void crearlistado() {


        baseDatos.collection("turismo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            int[] texto={R.string.descripcion1,R.string.descripcion2,R.string.descripcion3};
                            int contador=0;

                            for(QueryDocumentSnapshot document: task.getResult()){

                                String nombre=document.get("nombre").toString();
                                String rango=document.get("rango").toString();
                                String hora=document.get("hora").toString();
                                String foto =document.get("foto").toString();
                                listadeDatos.add(new Turismo(nombre,rango,hora,getString(texto[contador]),foto));
                                contador++;

                            }
                            AdaptadorLista adaptador=new AdaptadorLista(listadeDatos);
                            listado.setAdapter(adaptador);

                        }else{
                            Toast.makeText(Home.this, "Error consultando datos", Toast.LENGTH_SHORT).show();
                        }

                    }
                });





    }

    // metodo que pinta el menu en esta actividad
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;

    }
    // metodo para configurar idioma

    public void cambiarIdioma(String lenguaje)
    {
        Locale idioma=new Locale(lenguaje);//tipo de dato local que recibe el idioma deseado
        Locale.setDefault(idioma);// se establece el idioma deseado

        Configuration configuracionTelefono= getResources().getConfiguration();
        configuracionTelefono.locale=idioma;
        getBaseContext().getResources().updateConfiguration(configuracionTelefono,getBaseContext().getResources().getDisplayMetrics());


    }

    // MEtodo que controla la accion  de ca opcion del menu

    public boolean onOptionsItemSelected(MenuItem opcion)
    {
        int id=opcion.getItemId();

        switch (id){

            case (R.id.opcion1):
                Intent intent=new Intent(Home.this,Perfil.class);
                startActivity(intent);
                break;
            case (R.id.opcion2):
                this.cambiarIdioma("en");
                Intent intent2=new Intent(Home.this,Home.class);
                startActivity(intent2);
                break;

            case (R.id.opcion3):
                this.cambiarIdioma("es");
                Intent intent3=new Intent(Home.this,Home.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(opcion);
    }
}