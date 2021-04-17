package com.example.turismo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //ATRIBUTOS
    EditText cajaNumerodePersonas;
    Button botonCalcularPaquete;
    TextView cajaResultado,nombreActividad,rangodeEdad,horaActividad,descripcionActividad;
    ImageView fotoActividad;

    Turismo actividad;


    int paquete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaNumerodePersonas=findViewById(R.id.paqueteMain);//edittexts
        botonCalcularPaquete=findViewById(R.id.botonCalcular); //boton
        cajaResultado=findViewById(R.id.resultado); //textView

        nombreActividad=findViewById(R.id.nombreActividadMain);
        rangodeEdad=findViewById(R.id.rangodeEdadMain);
        horaActividad=findViewById(R.id.horarioActividadMain);
        descripcionActividad=findViewById(R.id.descripcionActividadMain);
        fotoActividad=findViewById(R.id.fotoTurismoMain);

        actividad=(Turismo) getIntent().getSerializableExtra("turismo");

        nombreActividad.setText(actividad.getNombreActividad());
        rangodeEdad.setText(actividad.getRangodeEdad());
        horaActividad.setText(actividad.horarioActividad);
        descripcionActividad.setText(actividad.getDescripcion());
        fotoActividad.setImageResource(actividad.getFotoActividad1());


        botonCalcularPaquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String datoCajaPersonas = cajaNumerodePersonas.getText().toString();

                int numeroPersonas = Integer.parseInt(datoCajaPersonas);
                cajaResultado.setText("Valor paquete turistico es" + numeroPersonas);
                if (numeroPersonas >=2) {
                    paquete = numeroPersonas * 50000;
                    cajaResultado.setText("Valor paquete turistico es:$" + paquete);

                }
                else {


                    paquete = 70000;
                    cajaResultado.setText("Valor paquete turistico es:$" + paquete);


                }



            }
        }) ;
    }
}