package personaje;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase Personaje, es de tipo abstracta porque en un futuro añadiré métodos abstractos,
 * también es la clase madre de la que heredarán las variables de vida y energia las clases Jugador y Zombie
 */
public abstract class Personaje {
    private int vida;
    private int energia;

    /**
     * Crea objetos personaje
     * @param vida int con la vida del personaje
     * @param energia int con la energía del personaje
     */
    public Personaje(int vida, int energia) {
        this.vida = vida;
        this.energia = energia;
    }

    /**
     * Método que devuelve la vida del personaje
     * @return Devuelve int con la vida del personaje
     */
    public int getVida() {
        return vida;
    }

    /**
     * Establece nueva vida para el personaje
     * @param vida int de la nueva vida del personaje
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Método que devuelve la energía del personaje
     * @return Devuelve int con la energía del personaje
     */
    public int getEnergia() {
        return energia;
    }

    /**
     * Establece nueva energía para el personaje
     * @param energia int de la nueva energía del personaje
     */
    public void setEnergia(int energia) {
        this.energia = energia;
    }

    @Override
    public String toString() {
        return "Personaje [vida=" + vida + ", energia=" + energia + "]";
    }    
}