package zombiesurvivor;

import utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import personaje.Jugador;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase principal del videojuego **ZombieSurvivor**. Controla el flujo general del programa
 * y ofrece un menú interactivo para que el jugador pueda:
 * - Leer las normas del juego.
 * - Empezar una nueva partida.
 * - Consultar las credenciales del autor.
 * - Visualizar las partidas guardadas en un fichero o en una base de datos.
 * - Salir del juego.
 * 
 * Además, contiene métodos auxiliares para mostrar el título del juego, imprimir las normas,
 * mostrar las credenciales del autor, y visualizar el historial de partidas tanto desde fichero como desde la base de datos MySQL.
 */
public class ZombieSurvivor {
    // Creo constantes para los colores que serán para toda la clase y todos los métodos de esta clase
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_PURPLE = "\u001B[35m";

    /**
     * Método creado para controlar el juego mediante la opción seleccionada por el jugador
     */
    public static void controlarJuego(){
        // Llamada al método imprimeTitulo
        imprimeTitulo();

        int opcionMenuSeleccionado;
        final int FINALIZARPROGRAMA = 6;
        do {
            // Llamada al método menu y el número escrito por el usuario determina que hace en el switch case
            opcionMenuSeleccionado = opcionMenu();

            switch (opcionMenuSeleccionado) {
                case 1:
                    imprimeNormas();
                    break;

                case 2:
                    Jugador jugador = new Jugador(Utilidades.leerEntrada("Escribe el nombre del jugador: "), 90, 80);
                    Partida partida = new Partida(jugador.getNombre());
                
                    Juego juego = new Juego(jugador, partida);

                    juego.iniciarJuego();
                    break;

                case 3:
                    imprimeCredenciales();
                    break;
                
                case 4:
                    verPartidasFichero();
                    break;

                case 5:
                    verPartidadBBDD();
                    break;

                case 6:
                    System.out.println("\nFin del programa. ¡Gracias por jugar!");
                    break;
            }
        } while (opcionMenuSeleccionado != FINALIZARPROGRAMA);
        Utilidades.cerrarScanner();
    }

    /**
     * Método creado para el menú del videojuego
     * @return Devuelve una de las opciones del Switch case al leer el dato introducido por el usuario
     */
    private static int opcionMenu() {
        int opcionMenu = -1;

        do {
            System.out.println(COLOR_PURPLE + "\t\t\t\t  __  __ _____ _   _ _   _ \n" +
                    "\t\t\t\t |  \\/  | ____| \\ | | | | |\n" +
                    "\t\t\t\t | |\\/| |  _| |  \\| | | | |\n" +
                    "\t\t\t\t | |  | | |___| |\\  | |_| |\n" +
                    "\t\t\t\t |_|  |_|_____|_| \\_|\\___/ \n" + COLOR_RESET);
            String opcionesMenu = "1) Normas y reglas del juego" +
                                "\n2) Empezar a jugar" +
                                "\n3) Credenciales y fecha de creación" +
                                "\n4) Ver partidas jugadas via fichero" +
                                "\n5) Ver partidas jugadas via BBDD" +
                                "\n6) Salir del juego\n";

            opcionMenu = Utilidades.leerEntrada(opcionesMenu, 1, 6);
            
        } while (opcionMenu == -1);
        return opcionMenu;
    }

    /**
     * Método creado para imprimir el título del videojuego
     */
    private static void imprimeTitulo() {
        System.out.println(COLOR_RED
                + "███████╗ ██████╗ ███╗   ███╗██████╗ ██╗███████╗███████╗██╗   ██╗██████╗ ██╗   ██╗██╗██╗   ██╗ ██████╗ ██████╗ \n" +
                "╚══███╔╝██╔═══██╗████╗ ████║██╔══██╗██║██╔════╝██╔════╝██║   ██║██╔══██╗██║   ██║██║██║   ██║██╔═══██╗██╔══██╗\n" +
                "  ███╔╝ ██║   ██║██╔████╔██║██████╔╝██║█████╗  ███████╗██║   ██║██████╔╝██║   ██║██║██║   ██║██║   ██║██████╔╝\n" +
                " ███╔╝  ██║   ██║██║╚██╔╝██║██╔══██╗██║██╔══╝  ╚════██║██║   ██║██╔══██╗╚██╗ ██╔╝██║╚██╗ ██╔╝██║   ██║██╔══██╗\n" +
                "███████╗╚██████╔╝██║ ╚═╝ ██║██████╔╝██║███████╗███████║╚██████╔╝██║  ██║ ╚████╔╝ ██║ ╚████╔╝ ╚██████╔╝██║  ██║\n" +
                "╚══════╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═══╝   ╚═════╝ ╚═╝  ╚═╝\n"
                + COLOR_RESET + "");
    }

    /**
     * Método creado que imprime las normas si eliges la opción 1 del método menu
     */
    private static void imprimeNormas() {
        final String NORMAS = COLOR_RED + "- " + COLOR_RESET
                    + "Aparecerás en un lugar aleatorio y tienes que moverte por el tablero introduciendo una coordenada: " +
                    COLOR_RED + "\n\t1. " + COLOR_RESET + "Norte (N) dirección arriba" +
                    COLOR_RED + "\n\t2. " + COLOR_RESET + "Noreste (NE) dirección arriba a la derecha" +
                    COLOR_RED + "\n\t3. " + COLOR_RESET + "Este (E) dirección a la derecha" +
                    COLOR_RED + "\n\t4. " + COLOR_RESET + "Sudeste (SE) dirección abajo a la derecha" +
                    COLOR_RED + "\n\t5. " + COLOR_RESET + "Sur (S) dirección abajo" +
                    COLOR_RED + "\n\t6. " + COLOR_RESET + "Sudoeste (SO) dirección abajo a la izquierda" +
                    COLOR_RED + "\n\t7. " + COLOR_RESET + "Oeste (O) dirección izquierda" +
                    COLOR_RED + "\n\t8. " + COLOR_RESET + "Noroeste (NE) dirección arriba a la izquierda." +
                    COLOR_RED + "\n- " + COLOR_RESET
                    + "Si no añades ninguno de los caracteres que está entre paréntesis te volverá a preguntar otra vez para que introduzcas alguno de los caracteres solicitados." +
                    COLOR_RED + "\n- " + COLOR_RESET
                    + "Si deseas salir de la partida tendrás que introducir (SA) de Salida o llegar al Lugar Seguro." +
                    COLOR_RED + "\n- " + COLOR_RESET + "No se distingue entre mayúsculas y minúsculas." +
                    COLOR_RED + "\n- " + COLOR_RESET
                    + "Hay lugares en los que recuperas siempre vida y resistencia que son campamentos" +
                    COLOR_RED + "\n- " + COLOR_RESET
                    + "También hay lugares en los que ganaras o perderás vida dependiendo de tus elecciones de toma de caminos." +
                    COLOR_RED + "\n- " + COLOR_RESET
                    + "Debes controlar la vida y la energía que tienes para no perder la partida.";
        Utilidades.pulsaTecla(NORMAS);
    }

    /**
     * Método creado que imprime las credenciales si eliges la opción 3 del método menu
     */
    private static void imprimeCredenciales() {
        final String CREDENCIALES = "\nVideojuego creado por Guillermo Sierra Castejón, fecha de inicio 27/12/2024 ";
        Utilidades.pulsaTecla(CREDENCIALES);
    }

    /**
     * Muestra por consola las partidas guardadas en el fichero "partidas.txt".
     * 
     * El usuario puede elegir entre dos opciones:
     * 1) Ver únicamente las partidas asociadas a un jugador específico (introduciendo su nombre).
     * 2) Ver todas las partidas almacenadas en el archivo.
     * 
     * Si el fichero no existe, se notifica al usuario y se finaliza el método.
     * En caso de error durante la lectura del archivo, también se informa al usuario.
     * 
     * El método utiliza un bucle para validar la opción introducida por el usuario
     * y maneja posibles errores de entrada no numérica mediante excepciones.
     */
    private static void verPartidasFichero() {
        System.out.println("\n¿QUÉ DESEAS VER?");
        System.out.println("1) Ver partidas de un jugador");
        System.out.println("2) Ver TODAS las partidas guardadas\n");

        int opcion = -1;
        do {
            try {
                String entrada = Utilidades.leerEntrada("Elige una opción (1 o 2): ");
                opcion = Integer.parseInt(entrada);
                if (opcion < 1 || opcion > 2) {
                    System.out.println("Número no válido. Introduzca un número entre el 1 y el 2.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Introduzca un número (1 o 2).\n");
            }
        } while (opcion != 1 && opcion != 2);

        String nombreArchivo = "partidas.txt";
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("No hay partidas guardadas. El archivo no existe.");
            Utilidades.pulsaTecla("");
            return;
        }

        if (opcion == 1) {
            // Opción 1: ver partidas de un jugador concreto
            String jugadorBuscado = Utilidades.leerEntrada("Introduce el nombre del jugador para ver sus partidas: ").toLowerCase();

            System.out.println("\n------ PARTIDAS GUARDADAS DE " + jugadorBuscado.toUpperCase() + " ------");

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean hayCoincidencias = false;

                while ((linea = br.readLine()) != null) {
                    if (linea.toLowerCase().startsWith(jugadorBuscado)) {
                        System.out.println(linea);
                        hayCoincidencias = true;
                    }
                }

                if (!hayCoincidencias) {
                    System.out.println("No se encontraron partidas guardadas para ese jugador.");
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de partidas: " + e.getMessage());
            }

        } else {
            // Opción 2: ver todas las partidas
            System.out.println("\n------ TODAS LAS PARTIDAS GUARDADAS ------");
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de partidas: " + e.getMessage());
            }
        }
        Utilidades.pulsaTecla("");
    }

    /**
     * Muestra por consola el historial de partidas almacenadas en la base de datos MySQL.
     * 
     * - Se establece la conexión a la base de datos mediante la clase {@code ConexionBBDD}.
     * - Se obtienen todas las partidas almacenadas y se guardan en un {@code ArrayList}.
     * - Se cierra la conexión con la base de datos.
     * - Se imprime una tabla con los datos de cada partida:
     *   nombre del jugador, hora de inicio, hora de fin, número de movimientos,
     *   resultado (victoria o derrota) y duración de la partida.
     * 
     * Al final, se espera a que el usuario pulse una tecla para continuar.
     */
    private static void verPartidadBBDD(){
        ConexionBBDD conexionBBDD;
        conexionBBDD = new ConexionBBDD();

        ArrayList<Partida> historicoPartidasBBDD = conexionBBDD.getPartidasBBDD();
        conexionBBDD.desconexionBD();

        System.out.printf("%-12s | %-19s | %-19s | %-5s | %-10s | %-10s%n",
        "Jugador", "Inicio", "Fin", "Movimiento", "Resultado", "Duración");

        for (Partida partida : historicoPartidasBBDD) {
            System.out.printf("%-12s | %-10s | %-10s | %-10d | %-10s | %-10s%n",
                        partida.getNombre(),
                        partida.getHoraInicioFormateada(),
                        partida.getHoraFinFormateada(),
                        partida.getNumMovimiento(),
                        (partida.getVictoria() ? "Victoria" : "Derrota"),
                        partida.getDuracionSec());
        }
        Utilidades.pulsaTecla("");
    }
}