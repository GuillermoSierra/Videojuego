import zombiesurvivor.ZombieSurvivor;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase principal que inicia la ejecución del juego ZombieSurvivor. 
 * Llama al método que controla el flujo del juego desde la clase ZombieSurvivor.
 */ 
public class Main {
    /**
     * Método principal que inicia la ejecución del juego. 
     * Llama a `controlarJuego()` en la clase `ZombieSurvivor` para gestionar el flujo del juego.
     * @param argumentos Parámetros de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] argumentos) {
        ZombieSurvivor.controlarJuego();
    }
}
