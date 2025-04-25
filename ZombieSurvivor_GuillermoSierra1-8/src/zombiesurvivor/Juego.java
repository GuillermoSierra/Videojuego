package zombiesurvivor;

import mapa.Mapa;
import personaje.Jugador;
import mapa.Lugar;
import bbdd.ConexionBBDD;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase encargada de gestionar el desarrollo de una partida del videojuego ZombieSurvivor.
 * Controla la lógica principal del juego, incluyendo los movimientos del jugador en el mapa, 
 * la interacción con los distintos lugares (como campamentos o zonas con zombis), la gestión de vida 
 * y energía del jugador, y las condiciones de victoria o derrota.
 * 
 * Además, se encarga de guardar el resultado de la partida tanto en un archivo como en una base de datos,
 * y de mostrar el estado actual del jugador tras cada movimiento.
 * 
 * El ciclo de juego se ejecuta dentro del método {@code iniciarJuego()}, que representa una partida completa
 * desde que se crea hasta que termina por victoria, derrota o salida manual.
 */
public class Juego {

    private Jugador jugador;
    private Partida partida;
    private Mapa mapa;
    private ConexionBBDD conexionBBDD;
    private static ArrayList<Partida> historialPartidas = new ArrayList<>();

    private final String COLOR_RESET = "\u001B[0m";
    private final String COLOR_RED = "\u001B[31m";
    private final String COLOR_GREEN = "\u001B[32m";
    private final String COLOR_YELLOW = "\u001B[33m";
    private final String COLOR_BLUE = "\u001B[34m";
    private final String COLOR_PURPLE = "\u001B[35m";

    /**
     * Método constructor de la clase Juego
     */
    public Juego(Jugador jugador, Partida partida) {
        this.jugador = jugador;
        this.partida = partida;

        int tamanioMaxX = 6;
        int tamanioMaxY = 6;

        mapa = new Mapa(tamanioMaxX, tamanioMaxY);
        // mapa.imprimeMapaLugares();

        Lugar lugar = mapa.getLugarPosJugador();
        System.out.println(COLOR_PURPLE + jugador.getNombre() + COLOR_RESET + " estás es un/a: " + COLOR_BLUE + lugar.getNombre() + COLOR_RESET +
                            " y tienes " + COLOR_GREEN + "vida: " + jugador.getVida() + COLOR_RESET + " y " + COLOR_YELLOW
                            + "energia: " + jugador.getEnergia() + COLOR_RESET);

        conexionBBDD = new ConexionBBDD();
    }
    
    /**
     * Método que inicia el juego y será llamado desde la main que está en ZombieSurvivor
     */
    public void iniciarJuego(){
        // Este do while te pregunta y lee la opcion que eliges para moverte
        int estadoMovimiento;
        do {

            mapa.actualizarTablero();
            mapa.mostrarTablero();
            String desplazamiento = mapa.leerMovimiento();
            estadoMovimiento = mapa.movimiento(desplazamiento);

            if (estadoMovimiento == mapa.getMovimientoCorrecto()) {
                partida.incrementarMovimiento();

                Lugar lugar = mapa.getLugarPosJugador();
                // Llama al método modificarAtributo para modificar la vida
                // La vida que se optiene de lugar es positiva y la del zombie es negativa, por eso sumanos
                jugador.modificarVidaEnergiaJugador("vida", lugar.getVidaObtenida() + mapa.getPosVidaQuitaZombie());
                // Llama al método modificarAtributo para modificar la energía
                // La energía que se optiene de lugar es positiva, el coste de energia por desplazartees negativo y la que te puede quitar el zombie es negativa, por eso sumamos
                jugador.modificarVidaEnergiaJugador("energia", lugar.getEnergiaObtenida() + lugar.getCosteEnergia() + mapa.getPosEnergiaQuitaZombie());

                imprimeEstadoJugadorMapa();
            }

            // La exclamación de !desplazamiento.equals... niega, quiere decir que mientras
            // NO sea Lugar seguro y las otras opciones que siga haciendo el do
        } while (!mapa.getLugarPosJugador().getEsLugarSeguro()
                && estadoMovimiento != mapa.getMovimientoSalir()
                && jugador.getVida() > jugador.getMIN_VIDA()
                && jugador.getEnergia() > jugador.getMIN_ENERGIA());

        if (jugador.getVida() <= jugador.getMIN_VIDA()) {
            System.out.println("\nTe has quedado sin vida.");
        }
        if (jugador.getEnergia() <= jugador.getMIN_ENERGIA()) {
            System.out.println("\nTe has queado sin energía.");
        }
        if (jugador.getVida() <= jugador.getMIN_VIDA() || jugador.getEnergia() <= jugador.getMIN_ENERGIA()) {
            System.out.println(COLOR_RED + "GAME OVER..." + COLOR_RESET);
        }

        if (mapa.getLugarPosJugador().getEsLugarSeguro()) {
            System.out.println("\nEnhorabuena, has llegado al lugar seguro.");
            partida.partidaGanada();
        }

        // Acaba la partida y la añade al historial de partidas
        partida.terminarPartida();
        historialPartidas.add(partida);

        // Guarda la partida jugada en el fichero Partidas.txt
        guardarPartidaEnArchivo(partida);

        conexionBBDD.guardaPartidaBBDD(partida);
        conexionBBDD.desconexionBD();
    };

    /**
     * Método que imprime por pantalla el estado de vida y energía del jugador 
     */
    private void imprimeEstadoJugadorMapa(){
        Lugar lugar = mapa.getLugarPosJugador();
        
        System.out.println(COLOR_PURPLE + jugador.getNombre() + COLOR_RESET + " estás es un/a: " + COLOR_BLUE + lugar.getNombre() + COLOR_RESET +
        " y varía la " + COLOR_GREEN + "vida: " + (lugar.getVidaObtenida() + mapa.getPosVidaQuitaZombie()) + COLOR_RESET +
        " y la " + COLOR_YELLOW + "energia: " + (lugar.getEnergiaObtenida() + lugar.getCosteEnergia() + mapa.getPosEnergiaQuitaZombie()) + COLOR_RESET +
        ". Por tanto tienes " + COLOR_GREEN + "vida: " + jugador.getVida() + COLOR_RESET + " y " + COLOR_YELLOW + "energia: " + jugador.getEnergia() + COLOR_RESET);

        System.out.println("El/La " + COLOR_BLUE + lugar.getNombre() + COLOR_RESET + " aporta vida: " + COLOR_GREEN + lugar.getVidaObtenida() + COLOR_RESET +
        ", energía conseguida: " + COLOR_YELLOW + lugar.getEnergiaObtenida() + COLOR_RESET + ", energía consumida para ir al lugar: " + COLOR_YELLOW + lugar.getCosteEnergia() + COLOR_RESET);
        
        if (lugar.getTieneZombie()) {
            System.out.println("En este lugar hay " + lugar.getNumZombies() + " zombies" + lugar.toStringZombies());
            
        } else {
            System.out.println("En este lugar no hay zombies.");
        }
        
    }

    /**
     * Guarda los datos de una partida en el archivo "Partidas.txt".
     * 
     * - Si el archivo no existe o está vacío, se escribe primero la cabecera con los títulos de las columnas.
     * - Luego, se añade una línea con los datos de la partida: nombre del jugador, hora de inicio y fin,
     *   número de movimientos, resultado (victoria o derrota) y duración formateada.
     * 
     * El archivo se abre en modo "append" para no sobrescribir el contenido existente.
     * 
     * @param p Objeto {@code Partida} que contiene toda la información a guardar.
     */
    private void guardarPartidaEnArchivo(Partida p) {
        File archivo = new File("Partidas.txt");
        boolean archivoExiste = archivo.exists();
        boolean archivoVacio = archivo.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
            if (!archivoExiste || archivoVacio) {
                writer.printf("%-12s | %-19s | %-19s | %-5s | %-10s | %-10s%n",
                            "Jugador", "Inicio", "Fin", "Movimiento", "Resultado", "Duración");
            }

            writer.printf("%-12s | %-10s | %-10s | %-10d | %-10s | %-10s%n",
                        p.getNombre(),
                        p.getHoraInicioFormateada(),
                        p.getHoraFinFormateada(),
                        p.getNumMovimiento(),
                        (p.getVictoria() ? "Victoria" : "Derrota"),
                        p.getDuracionFormateada());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}