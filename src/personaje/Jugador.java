package personaje;

import java.util.ArrayList;

import zombiesurvivor.Partida;
import utilidades.Constantes;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Representa al jugador en el juego, heredando de la clase Personaje. 
 * Gestiona su nombre, vida y energía, permitiendo modificar estos atributos en función de las acciones dentro del juego.
 */
public class Jugador extends Personaje{
    private String nombre;
    private ArrayList<Partida> partidas;

    /**
     * Método constructor de Jugador, crea objetos Jugador
     * @param nombre String del nombre del jugador
     * @param vida int de la vida del jugador
     * @param energia int de la energia del jugador
     */
    public Jugador(String nombre, int vida, int energia) {
        super(vida, energia);
        this.nombre = nombre;
        this.partidas = new ArrayList<>();
    }

    /**
     * Devuelve el nombre del jugador
     * @return String con el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del jugador.
     * @param nombre El nuevo nombre que se asignará a la entidad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el valor mínimo permitido de vida en el juego.
     * @return Valor mínimo de vida.
     */
    public int getMIN_VIDA() {
        return Constantes.MIN_VIDA;
    }

    /**
     * Devuelve el valor mínimo permitido de energía en el juego.
     * @return Valor mínimo de energía.
     */
    public int getMIN_ENERGIA() {
        return Constantes.MIN_ENERGIA;
    }

    // Método para agregar una partida
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
    }
    
    // Método para obtener todas las partidas
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    /**
     * Modifica la vida o la energía del jugador.
     * @param tipo "vida" para modificar la vida, "energia" para modificar la energía.
     * @param variacion Cantidad a modificar (puede ser positiva o negativa).
     */
    public void modificarVidaEnergiaJugador(String tipo, int variacion) {
        if (tipo.equalsIgnoreCase("vida")) {
            int vida = this.getVida() + variacion;
            if (vida > Constantes.MAX_VIDA) { // valores configuracion como constantes
                vida = Constantes.MAX_VIDA;
            }
            if (vida < Constantes.MIN_VIDA) {
                vida = Constantes.MIN_VIDA;
            }
            this.setVida(vida);
        } else if (tipo.equalsIgnoreCase("energia")) {
            int energia = this.getEnergia() + variacion;
            
            if (energia > Constantes.MAX_ENERGIA) {
                energia = Constantes.MAX_ENERGIA;
            }
            if (energia < Constantes.MIN_ENERGIA) {
                energia = Constantes.MIN_ENERGIA;
            }
            
            this.setEnergia(energia);
        }
    }
}