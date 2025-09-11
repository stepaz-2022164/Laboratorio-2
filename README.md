# 🧠 Juego de Memoria - Laboratorio 2

Un juego de memoria implementado en Java donde dos jugadores compiten para encontrar la mayor cantidad de pares de cartas en un tablero configurable.

## 📋 Descripción del Proyecto

Este proyecto implementa un juego clásico de memoria (también conocido como "Concentración" o "Memory") donde los jugadores deben encontrar pares de cartas idénticas en un tablero. El juego está desarrollado siguiendo principios de programación orientada a objetos y ofrece una experiencia interactiva a través de la consola.

### 🎯 Características Principales

- **Juego para 2 jugadores**: Turnos alternados entre dos jugadores
- **Tablero configurable**: Dimensiones personalizables (2-6 filas, 2-8 columnas)
- **Sistema de puntuación**: Cada par encontrado suma un punto
- **Interfaz de consola intuitiva**: Navegación clara y fácil de usar
- **Validación de entrada**: Control de errores en la entrada del usuario
- **Múltiples partidas**: Posibilidad de jugar varias rondas consecutivas

### 🏗️ Arquitectura del Sistema

El proyecto está estructurado en dos paquetes principales:

#### `org.sergiotepaz.main`
- **Main**: Clase principal que maneja toda la interacción con el usuario a través de la consola

#### `org.sergiotepaz.model`
- **JuegoMemoria**: Controlador principal que gestiona la lógica del juego
- **Jugador**: Representa a un jugador con su nombre y puntuación
- **Tablero**: Administra la estructura del juego y las cartas
- **Carta**: Representa una carta individual con su estado y propiedades

## 🚀 Instalación y Configuración

### Prerrequisitos

- **Java 24** o superior
- **Apache Maven** 3.6 o superior


## 🎮 Instrucciones de Uso

### Inicio del Juego

1. **Registro de Jugadores**:
    - Al iniciar, se solicitará el nombre de ambos jugadores
    - Los nombres deben contener solo letras (se valida automáticamente)

2. **Configuración del Tablero**:
    - Selecciona el número de filas (2-6)
    - Selecciona el número de columnas (2-8)
    - El total de cartas debe ser par para formar pares válidos

### Durante la Partida

1. **Turnos**:
    - Los jugadores alternan turnos
    - En cada turno, un jugador debe seleccionar exactamente 2 cartas

2. **Selección de Cartas**:
    - Introduce las coordenadas en formato: `fila columna`
    - Ejemplo: `1 2` para seleccionar la carta en fila 1, columna 2
    - Las coordenadas van de 1 hasta el máximo de filas/columnas

3. **Reglas del Juego**:
    - Si las dos cartas seleccionadas son iguales: **PAR ENCONTRADO**
        - El jugador gana 1 punto
        - Las cartas permanecen visibles
        - El jugador mantiene su turno
    - Si las cartas son diferentes:
        - Las cartas se ocultan nuevamente
        - El turno pasa al otro jugador

4. **Final del Juego**:
    - El juego termina cuando se encuentran todos los pares
    - Gana el jugador con más pares encontrados
    - En caso de empate, se declara un empate

### Controles Durante el Juego

- **Enter**: Continuar después de mostrar las cartas seleccionadas
- **Coordenadas**: `fila columna` (ej: `2 3`)
- **Nueva partida**: `s` o `si` para jugar otra partida
- **Salir**: `n` o `no` para terminar el juego

## 🎲 Ejemplo de Partida

```
=================================================
        BIENVENIDOS AL JUEGO DE MEMORIA
=================================================

Ingrese los datos de los jugadores:

Nombre del Jugador 1: Ana
Nombre del Jugador 2: Carlos

¡Jugadores registrados!
Jugador 1: Ana
Jugador 2: Carlos

==================================================
           CONFIGURACION DEL TABLERO
==================================================
Configure las dimensiones del tablero:
(Nota: El total de cartas debe ser par para formar pares)

Ingrese el numero de filas (2-6): 2
Ingrese el numero de columnas (2-8): 4

Tablero configurado: 2x4 (8 cartas, 4 pares)

==================================================
              NUEVA PARTIDA INICIADA
==================================================

==================================================
                  TABLERO
==================================================
     1   2   3   4  
   +----+----+----+----+
1  | ?  | ?  | ?  | ?  |
   +----+----+----+----+
2  | ?  | ?  | ?  | ?  |
   +----+----+----+----+

--- INFORMACION DEL JUEGO ---
Turno de: Ana
Puntuacion Ana: 0 pares
Puntuacion Carlos: 0 pares

Seleccione una carta (fila columna, ej: 1 2): 1 1
Carta seleccionada: Numero 3 en posicion (1, 1)
```

## 🔧 Estructura de Archivos

```
Laboratorio-2/
├── pom.xml                                    # Configuración de Maven
├── README.md                                  # Este archivo
├── .gitignore                                # Archivos ignorados por Git
└── src/
    └── main/
        └── java/
            └── org/
                └── sergiotepaz/
                    ├── main/
                    │   └── Main.java         # Clase principal
                    └── model/
                        ├── Carta.java        # Clase Carta
                        ├── Jugador.java      # Clase Jugador
                        ├── JuegoMemoria.java # Controlador del juego
                        └── Tablero.java      # Clase Tablero
```

## 🔍 Diagrama UML

Para visualizar la estructura del proyecto, consulta el archivo `UML.png` que contiene el diagrama de clases UML completo del sistema.

## 🎨 Características Técnicas

- **Lenguaje**: Java 24
- **Patrón de diseño**: Modelo-Vista-Controlador (MVC)
- **Gestión de dependencias**: Maven
- **Interfaz**: Consola/Terminal
- **Validación**: Entrada robusta con manejo de errores
- **Aleatoriedad**: Distribución aleatoria de cartas en cada partida

## 🐛 Solución de Problemas

### Errores Comunes

1. **"Coordenadas fuera de rango"**:
    - Verifica que las coordenadas estén dentro del rango del tablero configurado

2. **"No se puede seleccionar esa carta"**:
    - La carta ya está emparejada
    - Ya seleccionaste esa carta en el turno actual
    - Ya tienes 2 cartas seleccionadas

3. **"Formato incorrecto"**:
    - Usa el formato: `fila columna` (ej: `1 2`)
    - Separa los números con un espacio

## 👥 Créditos

Desarrollado por Sergio Tepaz como parte del Laboratorio 2.

## 📄 Licencia

Este proyecto es para fines educativos y de aprendizaje.