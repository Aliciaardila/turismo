package com.example.turismo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //ATRIBUTOS
    EditText cajanombre,cajaNumerodePersonas;
    TextView cajaResultado,nombreActividad,rangodeEdad,horaActividad,descripcionActividad;
    Button botonCalcularPaquete;
    ImageView fotoActividad;
    Turismo actividad;

    // atributo q almacena la conexion a la bd
    FirebaseFirestore baseDatos= FirebaseFirestore.getInstance();

    //atributo que crea la estructura de la coleccion a almacenar
    Map<String, Object> reservacion= new HashMap<>();
    int paquete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instancias de las vistas enlanzadolas con la logica
        cajanombre=findViewById(R.id.nombre);
        cajaNumerodePersonas=findViewById(R.id.paqueteMain);//
        cajaResultado=findViewById(R.id.resultado); //textView

        nombreActividad=findViewById(R.id.nombreActividadMain);
        rangodeEdad=findViewById(R.id.rangodeEdadMain);
        horaActividad=findViewById(R.id.horarioActividadMain);
        botonCalcularPaquete=findViewById(R.id.botonCalcular); //boton
        descripcionActividad=findViewById(R.id.descripcionActividadMain);
        fotoActividad=findViewById(R.id.fotoTurismoMain);

        actividad=(Turismo) getIntent().getSerializableExtra("turismo");


        nombreActividad.setText(actividad.getNombreActividad());
        rangodeEdad.setText(actividad.getRangodeEdad());
        horaActividad.setText(actividad.getHorarioActividad());
        descripcionActividad.setText(actividad.getDescripcion());

        Picasso.with(MainActivity.this)
                .load(actividad.getFotoActividad1())
                .into(fotoActividad);


        // escuchador de clic boton calcular
        botonCalcularPaquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // se obtienen los valores de cada Editext, text

                String nombre=cajanombre.getText().toString();
                int numeroPersonas = Integer.parseInt(cajaNumerodePersonas.getText().toString());

                //String datoResultado=cajaResultado.getText().toString();
                if (numeroPersonas >=2) {
                    paquete = numeroPersonas * 50000;
                    cajaResultado.setText("Valor paquete turistico es:$" + paquete);
                }
                else {
                    paquete = 70000;
                    cajaResultado.setText("Valor paquete turistico es:$" + paquete);

                }

                //Armo los datos de la colección
                reservacion.put("nombre",nombre);
                reservacion.put("numeroPersonas",numeroPersonas);
                reservacion.put("valorReserva",paquete);


                registrarPaquete();




            }


        }) ;
    }

    private void  registrarPaquete(){

        baseDatos.collection("reservaciones")
                .add(reservacion)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        cajanombre.setText("");
                        cajaNumerodePersonas.setText("");
                        cajaResultado.setText("");
                        Toast.makeText(getApplicationContext(),"La reserva se ha registrado con exito",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_LONG).show();

                    }
                });

    }
}