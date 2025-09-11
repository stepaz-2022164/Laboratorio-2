package org.sergiotepaz.model;

/**
 * Representa una carta individual del tablero
 */
public class Carta {
    private int numero;
    private boolean visible;
    private boolean emparejada;
    private int posicionX;
    private int posicionY;
    
    public Carta(int numero, int x, int y) {
        this.numero = numero;
        this.posicionX = x;
        this.posicionY = y;
        this.visible = false;
        this.emparejada = false;
    }
    
    public void revelar() {
        this.visible = true;
    }
    
    public void ocultar() {
        this.visible = false;
    }
    
    public void marcarEmparejada() {
        this.emparejada = true;
        this.visible = true; // Las cartas emparejadas permanecen visibles
    }
    
    public boolean esIgual(Carta otraCarta) {
        return this.numero == otraCarta.numero;
    }
    
    public boolean puedeSerSeleccionada() {
        return !this.emparejada;
    }
    
    // Getters
    public int getNumero() {
        return numero;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public boolean isEmparejada() {
        return emparejada;
    }
    
    public int getPosicionX() {
        return posicionX;
    }
    
    public int getPosicionY() {
        return posicionY;
    }
}