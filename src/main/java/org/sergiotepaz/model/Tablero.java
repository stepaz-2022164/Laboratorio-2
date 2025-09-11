package org.sergiotepaz.model;

import java.util.*;

/**
 * Administra la estructura del juego con todas las cartas
 */
public class Tablero {
    private Carta[][] cartas;
    private int filas;
    private int columnas;
    private int cartasEmparejadas;
    
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.cartas = new Carta[filas][columnas];
        this.cartasEmparejadas = 0;
        inicializarCartas();
    }
    
    private void inicializarCartas() {
        // Lista para almacenar todos los números (cada número aparece 2 veces)
        List<Integer> numerosDisponibles = new ArrayList<>();
        
        // Calcular cuántos pares necesitamos
        int totalCartas = filas * columnas;
        int paresNecesarios = totalCartas / 2;
        
        // Generar pares de números (empezando desde 1)
        for (int i = 1; i <= paresNecesarios; i++) {
            numerosDisponibles.add(i);
            numerosDisponibles.add(i);
        }
        
        // Mezclar aleatoriamente
        Collections.shuffle(numerosDisponibles);
        
        // Asignar números a las cartas
        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                cartas[i][j] = new Carta(numerosDisponibles.get(index), i, j);
                index++;
            }
        }
    }
    
    public Carta obtenerCarta(int x, int y) {
        if (x >= 0 && x < filas && y >= 0 && y < columnas) {
            return cartas[x][y];
        }
        return null;
    }
    
    public boolean todasCartasEmparejadas() {
        return cartasEmparejadas == (filas * columnas);
    }
    
    public void reiniciarTablero() {
        this.cartasEmparejadas = 0;
        inicializarCartas();
    }
    
    public void incrementarCartasEmparejadas() {
        this.cartasEmparejadas += 2; // Se empareja un par
    }
    
    // Getters
    public int getFilas() {
        return filas;
    }
    
    public int getColumnas() {
        return columnas;
    }
    
    public Carta[][] getCartas() {
        return cartas;
    }
}