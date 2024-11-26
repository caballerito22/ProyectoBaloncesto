package com.example.proyectobaloncesto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Jugador implements Serializable {
    @PrimaryKey()

    int id;
    String nombre;
    Integer puntos_totales;
    Integer minutos_jugados;
    String fecha_nacimiento;
    String imagen;

    public Jugador(int id, String nombre, int puntos_totales, int minutos_jugados, String fecha_nacimiento, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.puntos_totales = puntos_totales;
        this.minutos_jugados = minutos_jugados;
        this.fecha_nacimiento = fecha_nacimiento;
        this.imagen = imagen;
    }
    public Jugador(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos_totales() {
        return puntos_totales;
    }

    public void setPuntos_totales(int puntos_totales) {
        this.puntos_totales = puntos_totales;
    }

    public int getMinutos_jugados() {
        return minutos_jugados;
    }

    public void setMinutos_jugados(int minutos_jugados) {
        this.minutos_jugados = minutos_jugados;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", puntos_totales=" + puntos_totales +
                ", minutos_jugados=" + minutos_jugados +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}



