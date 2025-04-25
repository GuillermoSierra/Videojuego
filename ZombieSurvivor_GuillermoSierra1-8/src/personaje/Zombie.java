package personaje;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase Zombie, hereda algunas variables de la clase madre que en este caso es Personaje,
 * la función de la clase Zombie es quitar vida al jugador
 */
public class Zombie extends Personaje{

    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_GREEN = "\u001B[32m";
    private static final String COLOR_YELLOW = "\u001B[33m";
    
    private final int VIDA_ENERGIA_POSITIVO = 26;
    private final int VIDA_ENERGIA_NEGATIVO = 35;

    /**
     * Método constructor de la clase zombie, sirve para quitar vida al jugador si se encuentra con algún zombie por el camino
     */
    public Zombie(){
        super(0,0);        
        // Este random genera un número del 0-100 y le resta 50 a este número
        int vida = (int) (Math.random() * VIDA_ENERGIA_POSITIVO) - VIDA_ENERGIA_NEGATIVO; // Un zombie quita vida del -10 al -35
        int energia= (int) (Math.random() * VIDA_ENERGIA_POSITIVO) - VIDA_ENERGIA_NEGATIVO; // Un zombie quita energía del -10 al -35

        this.setVida(vida);
        this.setEnergia(energia);
    }

    @Override
    public String toString() {
        return COLOR_GREEN + "Vida " + this.getVida() + COLOR_RESET + COLOR_YELLOW + " Energia " + this.getEnergia() + COLOR_RESET;
    }

    //En futuras versiones implementaremos acciones de interación de los zombies con el mapa, lugares y el jugador
    
}
