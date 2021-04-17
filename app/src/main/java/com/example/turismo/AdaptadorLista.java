package com.example.turismo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class AdaptadorLista extends  RecyclerView.Adapter<AdaptadorLista.viewHolder>{

    ArrayList<Turismo> listadeDatos;

    public AdaptadorLista(ArrayList<Turismo> listadeDatos) {
        this.listadeDatos = listadeDatos;
    }

    @NonNull
    @Override
    public AdaptadorLista.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vistaItemLista= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_de_lista,parent,false);


        return new viewHolder(vistaItemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorLista.viewHolder holder, int position) {
    holder.actualizarDatosDeItem(listadeDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listadeDatos.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nombreActividad,descripcionActividad,horarioActividad,rangodeEdad;
        ImageView fotoActividad1;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nombreActividad=itemView.findViewById(R.id.nombreActividad);
            horarioActividad=itemView.findViewById(R.id.horarioActividad);
            rangodeEdad=itemView.findViewById(R.id.rangodeEdad);
            fotoActividad1= itemView.findViewById(R.id.fotoActividad);



        }

        public void actualizarDatosDeItem(Turismo datos) {

        nombreActividad.setText(datos.getNombreActividad());
        horarioActividad.setText(datos.getHorarioActividad());
        rangodeEdad.setText(datos.getRangodeEdad());
        fotoActividad1.setImageResource(datos.getFotoActividad1());


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(itemView.getContext(),MainActivity.class);
                intent.putExtra("turismo",datos);
                itemView.getContext().startActivity(intent);

            }
          });
       }
    }
}
