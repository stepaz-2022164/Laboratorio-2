package org.sergiotepaz.model;
public class Jugador {
    private String nombre;
    private int puntuacion;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
    }
    
    public void incrementarPuntuacion() {
        this.puntuacion++;
    }
    
    public void reiniciarPuntuacion() {
        this.puntuacion = 0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
}