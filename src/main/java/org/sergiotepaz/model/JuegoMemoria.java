package org.sergiotepaz.model;

import java.util.ArrayList;

/**
 * Controla la lógica principal del juego
 */
public class JuegoMemoria {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Tablero tablero;
    private ArrayList<Carta> cartasSeleccionadas;
    private boolean juegoActivo;
    
    public JuegoMemoria(Jugador j1, Jugador j2, int filasTablero, int columnasTablero) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActual = j1; // Empieza el jugador 1
        this.tablero = new Tablero(2, 4); // Tablero 2x4 (8 cartas)
        this.cartasSeleccionadas = new ArrayList<>();
        this.juegoActivo = true;
    }
    
    public boolean seleccionarCarta(int x, int y) {
        Carta carta = tablero.obtenerCarta(x, y);
        
        // Validaciones
        if (carta == null || !carta.puedeSerSeleccionada() || 
            cartasSeleccionadas.contains(carta) || cartasSeleccionadas.size() >= 2) {
            return false;
        }
        
        // Revelar la carta y agregarla a las seleccionadas
        carta.revelar();
        cartasSeleccionadas.add(carta);
        
        // Si se han seleccionado 2 cartas, procesar el turno
        if (cartasSeleccionadas.size() == 2) {
            return true; // Indica que se debe procesar el turno
        }
        
        return true;
    }
    
    public void procesarTurno() {
        if (cartasSeleccionadas.size() != 2) {
            return;
        }
        
        Carta carta1 = cartasSeleccionadas.get(0);
        Carta carta2 = cartasSeleccionadas.get(1);
        
        if (carta1.esIgual(carta2)) {
            // Es un par válido
            carta1.marcarEmparejada();
            carta2.marcarEmparejada();
            jugadorActual.incrementarPuntuacion();
            tablero.incrementarCartasEmparejadas();
            
            // El jugador mantiene su turno
        } else {
            // No es un par, ocultar las cartas
            carta1.ocultar();
            carta2.ocultar();
            
            // Cambiar turno
            cambiarTurno();
        }
        
        // Limpiar cartas seleccionadas
        cartasSeleccionadas.clear();
        
        // Verificar si el juego ha terminado
        if (verificarFinJuego()) {
            juegoActivo = false;
        }
    }
    
    private void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
    
    public boolean verificarFinJuego() {
        return tablero.todasCartasEmparejadas();
    }
    
    public Jugador obtenerGanador() {
        if (jugador1.getPuntuacion() > jugador2.getPuntuacion()) {
            return jugador1;
        } else if (jugador2.getPuntuacion() > jugador1.getPuntuacion()) {
            return jugador2;
        }
        return null; // Empate
    }
    
    public void reiniciarJuego() {
        jugador1.reiniciarPuntuacion();
        jugador2.reiniciarPuntuacion();
        jugadorActual = jugador1;
        tablero.reiniciarTablero();
        cartasSeleccionadas.clear();
        juegoActivo = true;
    }
    
    public void reiniciarJuegoConNuevasDimensiones(int filas, int columnas) {
    jugador1.reiniciarPuntuacion();
    jugador2.reiniciarPuntuacion();
    jugadorActual = jugador1;
    tablero = new Tablero(filas, columnas); // Crear nuevo tablero con nuevas dimensiones
    cartasSeleccionadas.clear();
    juegoActivo = true;
}
    
    // Getters
    public Jugador getJugador1() {
        return jugador1;
    }
    
    public Jugador getJugador2() {
        return jugador2;
    }
    
    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public boolean isJuegoActivo() {
        return juegoActivo;
    }
    
    public ArrayList<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }
}