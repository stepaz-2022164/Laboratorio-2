package org.sergiotepaz.main;

import org.sergiotepaz.model.*;
import java.util.Scanner;

/**
 * Clase principal que maneja la interaccion con el usuario por consola
 */
public class Main {
    private static JuegoMemoria juegoMemoria;
    private static Scanner scanner = new Scanner(System.in);
    private static int filasTablero = 2;
    private static int columnasTablero = 4;
    
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("        BIENVENIDOS AL JUEGO DE MEMORIA");
        System.out.println("=================================================");
        
        mostrarLogin();
        
        while (true) {
            configurarTablero();
            iniciarNuevaPartida();
            
            System.out.print("\n¿Desean jugar otra partida? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            
            if (!respuesta.equals("s") && !respuesta.equals("si")) {
                System.out.println("\n¡Gracias por jugar! ¡Hasta la proxima!");
                break;
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarLogin() {
        System.out.println("\nIngrese los datos de los jugadores:\n");
        
        System.out.print("Nombre del Jugador 1: ");
        String nombreJ1 = validarEntrada(scanner.nextLine());
        
        System.out.print("Nombre del Jugador 2: ");
        String nombreJ2 = validarEntrada(scanner.nextLine());
        
        System.out.println("\n¡Jugadores registrados!");
        System.out.println("Jugador 1: " + nombreJ1);
        System.out.println("Jugador 2: " + nombreJ2);
        
        // Inicializar el juego con dimensiones por defecto
        Jugador jugador1 = new Jugador(nombreJ1);
        Jugador jugador2 = new Jugador(nombreJ2);
        juegoMemoria = new JuegoMemoria(jugador1, jugador2, filasTablero, columnasTablero);
    }
    
    private static void configurarTablero() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           CONFIGURACION DEL TABLERO");
        System.out.println("=".repeat(50));
        
        System.out.println("Configure las dimensiones del tablero:");
        System.out.println("(Nota: El total de cartas debe ser par para formar pares)");
        
        do {
            System.out.print("\nIngrese el numero de filas (2-6): ");
            filasTablero = validarNumero(2, 6);
            
            System.out.print("Ingrese el numero de columnas (2-8): ");
            columnasTablero = validarNumero(2, 8);
            
            int totalCartas = filasTablero * columnasTablero;
            
            if (totalCartas % 2 != 0) {
                System.out.println("Error: El total de cartas (" + totalCartas + 
                                 ") debe ser par. Intente nuevamente.");
            } else {
                System.out.println("\nTablero configurado: " + filasTablero + "x" + 
                                 columnasTablero + " (" + totalCartas + " cartas, " + 
                                 (totalCartas/2) + " pares)");
                break;
            }
        } while (true);
        
        // Actualizar el juego con las nuevas dimensiones
        juegoMemoria.reiniciarJuegoConNuevasDimensiones(filasTablero, columnasTablero);
    }
    
    private static void iniciarNuevaPartida() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              NUEVA PARTIDA INICIADA");
        System.out.println("=".repeat(50));
        
        // Ciclo principal del juego
        while (juegoMemoria.isJuegoActivo()) {
            mostrarTablero();
            mostrarInformacionTurno();
            
            if (procesarTurnoJugador()) {
                // Se seleccionaron 2 cartas, procesar turno
                System.out.println("\nProcesando turno...");
                
                // Mostrar las cartas seleccionadas brevemente
                mostrarCartasSeleccionadas();
                esperarTecla();
                
                juegoMemoria.procesarTurno();
                
                if (!juegoMemoria.isJuegoActivo()) {
                    break; // El juego termino
                }
            }
        }
        
        mostrarFinJuego();
    }
    
    private static void mostrarTablero() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                  TABLERO");
        System.out.println("=".repeat(50));
        
        Tablero tablero = juegoMemoria.getTablero();
        
        // Mostrar numeros de columna (1-N para el usuario)
        System.out.print("    ");
        for (int j = 1; j <= tablero.getColumnas(); j++) {
            System.out.printf("%2d  ", j);
        }
        System.out.println();
        
        // Linea superior del tablero
        System.out.print("   +");
        for (int j = 0; j < tablero.getColumnas(); j++) {
            System.out.print("----");
            if (j < tablero.getColumnas() - 1) {
                System.out.print("+");
            }
        }
        System.out.println("+");
        
        // Mostrar filas del tablero
        for (int i = 0; i < tablero.getFilas(); i++) {
            System.out.print((i + 1) + "  |"); // Mostrar numero de fila
            
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Carta carta = tablero.obtenerCarta(i, j);
                String contenido;
                
                if (carta.isEmparejada()) {
                    contenido = String.format("%2d ", carta.getNumero()); // Carta emparejada
                } else if (carta.isVisible()) {
                    contenido = String.format("%2d ", carta.getNumero()); // Carta visible temporalmente
                } else {
                    contenido = " ? "; // Carta oculta
                }
                
                System.out.print(contenido + "|");
            }
            System.out.println();
            
            // Linea separadora entre filas
            System.out.print("   +");
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print("----");
                if (j < tablero.getColumnas() - 1) {
                    System.out.print("+");
                }
            }
            System.out.println("+");
        }
    }
    
    private static void mostrarCartasSeleccionadas() {
        System.out.println("\nCARTAS SELECCIONADAS:");
        
        var cartasSeleccionadas = juegoMemoria.getCartasSeleccionadas();
        for (int i = 0; i < cartasSeleccionadas.size(); i++) {
            Carta carta = cartasSeleccionadas.get(i);
            System.out.println("Carta " + (i + 1) + ": Numero " + carta.getNumero() + 
                             " en posicion (" + (carta.getPosicionX() + 1) + ", " + 
                             (carta.getPosicionY() + 1) + ")");
        }
        
        // Mostrar el tablero con las cartas reveladas
        System.out.println("\nTABLERO ACTUAL:");
        mostrarTablero();
        
        // Evaluar si es par o no
        if (cartasSeleccionadas.size() == 2) {
            Carta carta1 = cartasSeleccionadas.get(0);
            Carta carta2 = cartasSeleccionadas.get(1);
            
            if (carta1.esIgual(carta2)) {
                System.out.println("¡PAR ENCONTRADO! " + juegoMemoria.getJugadorActual().getNombre() + 
                                 " mantiene el turno.");
            } else {
                System.out.println("No es un par. Cambiando turno...");
            }
        }
    }
    
    private static void mostrarInformacionTurno() {
        System.out.println("\n--- INFORMACION DEL JUEGO ---");
        System.out.println("Turno de: " + juegoMemoria.getJugadorActual().getNombre());
        System.out.println("Puntuacion " + juegoMemoria.getJugador1().getNombre() + ": " + 
                         juegoMemoria.getJugador1().getPuntuacion() + " pares");
        System.out.println("Puntuacion " + juegoMemoria.getJugador2().getNombre() + ": " + 
                         juegoMemoria.getJugador2().getPuntuacion() + " pares");
        
        // Mostrar cartas ya seleccionadas en este turno
        var cartasEnTurno = juegoMemoria.getCartasSeleccionadas();
        if (cartasEnTurno.size() > 0) {
            System.out.println("\nCartas seleccionadas en este turno:");
            for (int i = 0; i < cartasEnTurno.size(); i++) {
                Carta carta = cartasEnTurno.get(i);
                System.out.println((i + 1) + ". Numero " + carta.getNumero() + 
                                 " en (" + (carta.getPosicionX() + 1) + ", " + 
                                 (carta.getPosicionY() + 1) + ")");
            }
        }
    }
    
    private static boolean procesarTurnoJugador() {
        int cartasSeleccionadasEnTurno = juegoMemoria.getCartasSeleccionadas().size();
        
        while (cartasSeleccionadasEnTurno < 2) {
            System.out.print("\nSeleccione una carta (fila columna, ej: 1 2): ");
            String entrada = scanner.nextLine().trim();
            
            if (entrada.isEmpty()) {
                System.out.println("Por favor, ingrese las coordenadas.");
                continue;
            }
            
            String[] coordenadas = entrada.split("\\s+");
            
            if (coordenadas.length != 2) {
                System.out.println("Formato incorrecto. Use: fila columna (ej: 1 2)");
                continue;
            }
            
            try {
                int filaUsuario = Integer.parseInt(coordenadas[0]);
                int columnaUsuario = Integer.parseInt(coordenadas[1]);
                
                // Convertir de coordenadas de usuario (1-N) a coordenadas de arreglo (0-N-1)
                int fila = filaUsuario - 1;
                int columna = columnaUsuario - 1;
                
                // Validar rangos
                Tablero tablero = juegoMemoria.getTablero();
                if (fila < 0 || fila >= tablero.getFilas() || 
                    columna < 0 || columna >= tablero.getColumnas()) {
                    System.out.println("Coordenadas fuera de rango. Use filas 1-" + 
                                     tablero.getFilas() + " y columnas 1-" + 
                                     tablero.getColumnas());
                    continue;
                }
                
                if (juegoMemoria.seleccionarCarta(fila, columna)) {
                    Carta cartaSeleccionada = tablero.obtenerCarta(fila, columna);
                    cartasSeleccionadasEnTurno++;
                    
                    System.out.println("Carta seleccionada: Numero " + cartaSeleccionada.getNumero() + 
                                     " en posicion (" + filaUsuario + ", " + columnaUsuario + ")");
                    
                    // Mostrar el tablero actualizado despues de cada seleccion
                    mostrarTablero();
                } else {
                    System.out.println("No se puede seleccionar esa carta. Razones posibles:");
                    System.out.println("• La carta ya esta emparejada");
                    System.out.println("• Ya seleccionaste esta carta en este turno");
                    System.out.println("• Ya tienes 2 cartas seleccionadas");
                }
            } catch (NumberFormatException e) {
                System.out.println("Las coordenadas deben ser numeros enteros.");
            }
        }
        
        return true;
    }
    
    private static void mostrarFinJuego() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              ¡JUEGO TERMINADO!");
        System.out.println("=".repeat(50));
        
        // Mostrar tablero final
        mostrarTablero();
        
        // Mostrar puntuaciones finales
        System.out.println("\n--- PUNTUACIONES FINALES ---");
        System.out.println(juegoMemoria.getJugador1().getNombre() + ": " + 
                         juegoMemoria.getJugador1().getPuntuacion() + " pares");
        System.out.println(juegoMemoria.getJugador2().getNombre() + ": " + 
                         juegoMemoria.getJugador2().getPuntuacion() + " pares");
        
        // Mostrar ganador
        Jugador ganador = juegoMemoria.obtenerGanador();
        System.out.println("\n--- RESULTADO ---");
        if (ganador != null) {
            System.out.println("¡GANADOR: " + ganador.getNombre() + "!");
        } else {
            System.out.println("¡EMPATE! Ambos jugadores tienen la misma puntuacion.");
        }
    }
    
    private static int validarNumero(int min, int max) {
        int numero;
        while (true) {
            try {
                String entrada = scanner.nextLine().trim();
                numero = Integer.parseInt(entrada);
                
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.print("Debe ser un numero entre " + min + " y " + max + ". Intente nuevamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Debe ingresar un numero valido. Intente nuevamente: ");
            }
        }
    }
    
    private static String validarEntrada(String texto) {
        while (texto == null || texto.trim().isEmpty() || 
               !texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            System.out.print("Nombre invalido. Ingrese un nombre valido (solo letras): ");
            texto = scanner.nextLine();
        }
        return texto.trim();
    }
    
    private static void esperarTecla() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}