package com.example.turismo;

import java.io.Serializable;

public class Turismo implements Serializable {
    String nombreActividad, rangodeEdad,horarioActividad,descripcion;
    String fotoActividad1;

    public Turismo(String nombreActividad, String rangodeEdad, String horarioActividad, String descripcion, String fotoActividad1) {
        this.nombreActividad = nombreActividad;
        this.rangodeEdad = rangodeEdad;
        this.horarioActividad = horarioActividad;
        this.descripcion = descripcion;
        this.fotoActividad1 = fotoActividad1;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getRangodeEdad() {
        return rangodeEdad;
    }

    public void setrangodeEdad(String rangodeEdad) {
        this.rangodeEdad = rangodeEdad;
    }

    public String getHorarioActividad() {
        return horarioActividad;
    }

    public void setHorarioActividad(String horarioActividad) {
        this.horarioActividad = horarioActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoActividad1() {
        return fotoActividad1;
    }

    public void setFotoActividad1(String fotoActividad1) {
        this.fotoActividad1 = fotoActividad1;
    }
}
