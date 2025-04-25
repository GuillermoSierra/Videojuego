package mapa;

import java.util.Arrays;

import personaje.Zombie;
import utilidades.Constantes;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Representa una ubicación en el mapa del juego. Puede ser segura o peligrosa, 
 * afectar la vida y energía del jugador y contener zombies que infligen daño. 
 * También gestiona su nombre, posición y costos de energía.
 */
public class Lugar {

    private String nombre;
    private boolean esLugarSeguro;
    private int costeEnergia; // esto es la energía que le cuesta al jugador acceder a este lugar
    private int posX; // Posición del lugar en el eje X del lugar en el mapa
    private int posY; // Posición del lugar en el eje Y del lugar en el mapa
    private Zombie[] grupoZombie;
    private boolean tieneZombie; // No todos los lugares tienen zombies, hay lugares que puede tener hasta 3 zombies. Está relacionado con el atributo grupoZombie
    private int vidaObtenida; // Al llegar a un lugar el jugador obtiene vida en a base a lo que encuentre en el lugar. Maás adelante en futura versión veremos como añadir que este valor se base en pruebas, mini juegos, etc
    private int energiaObtenida; // El jugador podrá encontrar Comida, medicinas, etc
    
    /**
     * Método constructor de Lugar, crea objetos Lugar 
     * @param posX int de la posX del lugar
     * @param posY int de la posY del lugar
     */
    public Lugar(int posX, int posY, boolean esLugarSeguro) {
        this.esLugarSeguro = esLugarSeguro;
        this.posX = posX;
        this.posY = posY;

        this.tieneZombie = false;

        if (esLugarSeguro) {
            nombre = "Lugar Seguro";
            vidaObtenida = Constantes.MAX_VIDA;
            energiaObtenida = Constantes.MAX_ENERGIA;
        } else {
            int lugarAleatorio = (int) (Math.random() * Constantes.LUGARES.length);

            nombre = Constantes.LUGARES[lugarAleatorio];

            // Este random genera un número del 0-18 y le resta 20 a este número
            // Obtenemos un rango de osteEnergia de -2 a -20
            costeEnergia= (int) (Math.random() * Constantes.COSTE_ENERGIA_POSITIVO) - Constantes.COSTE_ENERGIA_NEGATIVO;

            vidaObtenida = (int) (Math.random() * Constantes.VIDA_ENERGIA_OBTENIDA);
            energiaObtenida = (int) (Math.random() * Constantes.VIDA_ENERGIA_OBTENIDA);
        }
    }

    /**
     * Devuelve el nombre del lugar
     * @return String del nombre del lugar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre al Lugar
     * @param nombre String del nuevo nombre del lugar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Indica si el lugar es un "Lugar Seguro" dentro del mapa del juego.
     * @return true si es un Lugar Seguro, false en caso contrario.
     */
    public boolean getEsLugarSeguro() {
        return esLugarSeguro;
    }

    /**
     * Establece si el lugar es un "Lugar Seguro" o no.
     * @param esLugarSeguro true para marcar el lugar como seguro, false para marcarlo como no seguro.
     */
    public void setEsLugarSeguro(boolean esLugarSeguro) {
        this.esLugarSeguro = esLugarSeguro;
    }

    /**
     * Devuelve la vida del lugar
     * @return int de la vida obtenida del lugar
     */
    public int getVidaObtenida() {
        return vidaObtenida;
    }

    /**
     * Establece nueva vida para el Lugar
     * @param vida int de la nueva vida obtenida de Lugar
     */
    public void setVidaObtenida(int vida) {
        this.vidaObtenida = vida;
    }

    /**
     * Devuelve la energia del lugar
     * @return int del energia quitada del lugar
     */
    public int getCosteEnergia() {
        return costeEnergia;
    }

    /**
     * Establece nueva energia quitada para el lugar
     * @param energia int de la nueva energia quitada del lugar
     */
    public void setCosteEnergia(int energia) {
        this.costeEnergia = energia;
    }

    /**
     * Devuelve la energía obtenida del lugar
     * @return int de la energía obtenida del lugar
     */
    public int getEnergiaObtenida() {
        return energiaObtenida;
    }

    /**
     * Establece nueva energía obtenida del lugar
     * @param energiaObtenida
     */
    public void setEnergiaObtenida(int energiaObtenida) {
        this.energiaObtenida = energiaObtenida;
    }

    /**
     * Devuelve la vida que quita el o los zombies
     * @return int con la vida quitada por el o los zombies
     */
    public int getVidaQuitaZombie(){
        int vidaQuitaZombie = 0;
        if (this.tieneZombie) {
            for(int i = 0; i < grupoZombie.length; i++){
                Zombie zombie = grupoZombie[i];
                vidaQuitaZombie += zombie.getVida();
            }
        }else{
            vidaQuitaZombie = 0;
        }

        return vidaQuitaZombie;
    }

    /**
     * Devuelve la energía que quita el o los zombies
     * @return int con la energia quitada por el o los zombies
     */
    public int getEnergiaQuitaZombie(){
        int energiaQuitaZombie = 0;
        if (this.tieneZombie) {
            for(int i = 0; i < grupoZombie.length; i++){
                Zombie zombie = grupoZombie[i];
                energiaQuitaZombie += zombie.getEnergia();
            }
        }else{
            energiaQuitaZombie = 0;
        }

        return energiaQuitaZombie;
    }

    /**
     * Método que indica si hay zombies en el grupo.
     * @return "true" si hay zombies, "false" si no hay.
     */
    public boolean getTieneZombie(){
        return this.tieneZombie;
    }

    /**
     * Método creado para saber cuantos zombies se generan en cada lugar
     */
    public void generarZombies(){
        try {
            int numZombiesLugar = (int) (Math.random() * 3) + 1;

            this.tieneZombie = true;
            grupoZombie = new Zombie[numZombiesLugar];

            for(int i = 0; i < grupoZombie.length; i++){
                grupoZombie[i] = new Zombie();
            }
        } catch (Exception e) {
            System.out.println("Error al generar zombies en los lugares.");
        }
        
    }

    /**
     * Método que obtiene el número de zombies en el grupo.
     * @return La cantidad de zombies si existen, o 0 si no hay zombies.
     */
    public int getNumZombies(){
        int numZombies;
        
        if (tieneZombie) {
            numZombies = grupoZombie.length;
        } else {
            numZombies = 0;
        }
        return numZombies;
    }

    /**
     * Método que devuelve una cadena con los zombies que tiene este lugar y la vida y energía que quitan.
     * Solo lo muestra si el lugar tieneZombies.
     * @return Una cadena de texto con la información de los zombies si existen, o una cadena vacía si no hay zombies.
     */
    public String toStringZombies(){
        String textoRetornado = "";
        
        if (tieneZombie) {
            for (int i = 0; i < grupoZombie.length; i++){
                textoRetornado = textoRetornado + ", Zombie " + (i+1) + " " + grupoZombie[i].toString();
            }
        }
        
        return textoRetornado;
    }

    @Override
    public String toString() {
        return "Lugar [nombre=" + nombre + ", esLugarSeguro=" + esLugarSeguro + ", costeEnergia=" + costeEnergia
                + ", posX=" + posX + ", posY=" + posY + ", grupoZombie=" + Arrays.toString(grupoZombie)
                + ", vidaObtenida=" + vidaObtenida + ", energiaObtenida=" + energiaObtenida + "]";
    }

    
}