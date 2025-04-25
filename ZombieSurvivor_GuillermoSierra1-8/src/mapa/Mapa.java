package mapa;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Representa el mapa del juego, compuesto por una cuadrícula de lugares donde el jugador puede moverse.
 * Gestiona la generación de lugares, la colocación de zombies y el control del movimiento del jugador. 
 * También maneja la representación visual del mapa mediante un tablero.
 */
import utilidades.Utilidades;

public class Mapa {

    private final String COLOR_RESET = "\u001B[0m";
    private final String COLOR_RED = "\u001B[31m";
    private final String COLOR_CYAN = "\u001B[36m";
    
    private int tamanioMaxX;
    private int tamanioMaxY;
    
    private int posInicialX;
    private int posInicialY;

    private Lugar[][] mapaLugares; // Lugares del Mapa, en cada casilla hay un lugar
    private String[][] tablero; // El tablero se usa como representación gráfica del mapa

    private int posJugadorX;
    private int posJugadorY;

    private final int MOVIMIENTO_SALIR = 1;
    private final int MOVIMIENTO_CORRECTO = 2;
    private final int MOVIMIENTO_ERRONEO = 3;

    /**
     * Método constructor de Mapa, crea objetos Mapa
     */
    public Mapa(int tamanioMaxX, int tamanioMaxY) {

        this.tamanioMaxX = tamanioMaxX;
        this.tamanioMaxY = tamanioMaxY;
        mapaLugares = new Lugar[tamanioMaxX][tamanioMaxY];
        
        inicializarTablero();

        // Posición inicial del jugador. Cojo la variable posX y posY para asignar un Lugar de aparicion aleatorio
        posInicialX = (int) (Math.random() * tamanioMaxX);
        posInicialY= (int) (Math.random() * tamanioMaxY);

        posJugadorX = posInicialX; // Esta es la posición X que variará en el mapa
        posJugadorY = posInicialY; // Esta es la posición Y que variará en el mapa

        addLugaresMapa();

        for (int i = 0; i < 18; i++) {
            Lugar[] lugarSinZombie = obtieneLugaresSinZombie();
            int posLugar = (int) (Math.random() * lugarSinZombie.length);
            lugarSinZombie[posLugar].generarZombies();
        }
    }

    /**
     * Genera y asigna lugares aleatorios en el mapa del juego. 
     * Se define una ubicación aleatoria como el "Lugar Seguro" y el resto de posiciones se asignan como lugares normales.
     */
    private void addLugaresMapa(){
        // Creo 2 variables para asignar el Lugar Seguro aleatoriamente en algún sitio del mapa
        int posSeguraX = (int) (Math.random() * tamanioMaxX);
        int posSeguraY = (int) (Math.random() * tamanioMaxY);
        
        // Recorre todo el mapa y asigna un lugar aleatorio a cada posición
        for (int x = 0; x < tamanioMaxX; x++) {
            for (int y = 0; y < tamanioMaxY; y++) {
                Lugar lugar;
                
                if (x == posSeguraX && y == posSeguraY) {
                    lugar = new Lugar(x, y, true);    
                } else {
                    lugar = new Lugar(x, y, false);
                }
                mapaLugares[x][y] = lugar;
            }
        }
    }

    /**
     * Devuelve el lugar del mapa correspondiente a las coordenadas dadas.
     * @param posX Coordenada X del lugar a obtener.
     * @param posY Coordenada Y del lugar a obtener.
     * @return Lugar en la posición especificada del mapa.
     */
    public Lugar getLugar(int posX, int posY){
        return mapaLugares[posX][posY];
    }

    /**
     * Método que obtiene el valor constante asociado a la acción de salir.
     * @return El valor de la constante "MOVIMIENTO_SALIR", que representa la acción de salida.
     */
    public int getMovimientoSalir(){
        return MOVIMIENTO_SALIR;
    }

    /**
     * Método que obtiene el valor constante asociado a un movimiento válido o correcto.
     * @return El valor de la constante "MOVIMIENTO_CORRECTO", que indica un movimiento permitido.
     */
    public int getMovimientoCorrecto(){
        return MOVIMIENTO_CORRECTO;
    }

    /**
     * Método que obtiene el lugar en el que se encuentra actualmente el jugador en el mapa.
     * @return El objeto "Lugar" correspondiente a la posición actual del jugador en el mapa.
     */
    public Lugar getLugarPosJugador(){
        return mapaLugares[posJugadorX][posJugadorY];
    }

    /**
     * Devuelve la posición actual del jugador en el eje X.
     * @return Coordenada X del jugador.
     */
    public int getPosJugadorX() {
        return posJugadorX;
    }

    /**
     * Devuelve la posición actual del jugador en el eje Y.
     * @return Coordenada Y del jugador.
     */
    public int getPosJugadorY() {
        return posJugadorY;
    }

    /**
     * Devuelve la vida quitada por un zombie en la posX y posY que se encuentre
     * @return int con la vida quitada por el zombie
     */
    public int getPosVidaQuitaZombie(){
        return mapaLugares[posJugadorX][posJugadorY].getVidaQuitaZombie();
    }

    /**
     * Devuelve la energía quitada por un zombie en la posX y posY que se encuentre
     * @return int con la energía quitada por un zombie en la posX y posY que se encuentre
     */
    public int getPosEnergiaQuitaZombie(){
        return mapaLugares[posJugadorX][posJugadorY].getEnergiaQuitaZombie();
    }

    /**
     * Método creado para revisar que lugares no tienen zombies y si no tienen se añaden en lugarAdmiteZombie
     * @return
     */
    private Lugar[] obtieneLugaresSinZombie(){
        // Obtenemos numero de lugares que admiten zombies para luego poder declarar el array con un tamaño concreto
        int numLugaresAdmiteZombie = 0;
        for (int x = 0; x < tamanioMaxX; x++) {
            for (int y = 0; y < tamanioMaxY; y++) {
                Lugar lugarAdmiteZombie = mapaLugares[x][y];
                if (!lugarAdmiteZombie.getTieneZombie() && x != posInicialX && y != posInicialY && !lugarAdmiteZombie.getNombre().equalsIgnoreCase("Lugar seguro")){
                    numLugaresAdmiteZombie++;                    
                }
            }
        }
        //numLugaresAdmiteZombie-=1; // restamos uno, que es el lugar inicial, El lugar seguro lo discrima el metodo admiteZombies
        // En un array metemos todos los lugares que admiten zombies, gracias a haber contado el numero de lugares que admiten zombies
        Lugar[] lugarSinZombie = new Lugar[numLugaresAdmiteZombie]; // restamos uno, que es el lugar inicial, El lugar seguro lo discrima el metodo admiteZombies
        int numLugar = 0;                
        for (int x = 0; x < tamanioMaxX; x++) {
            for (int y = 0; y < tamanioMaxY; y++) {
                Lugar lugarAdmiteZombie = mapaLugares[x][y];
                if (!lugarAdmiteZombie.getTieneZombie() && x != posInicialX && y != posInicialY && !lugarAdmiteZombie.getNombre().equalsIgnoreCase("Lugar seguro")){
                    lugarSinZombie[numLugar] = lugarAdmiteZombie;
                    numLugar++;
                }
            }
        }

        return lugarSinZombie;
    }

    /**
     * Método creado para controlar el movimiento en el mapa de juego
     * @param desplazamiento String para el desplazamiento que jugador
     * @return Devuelve Si es un movimiento correcto
     */
    public int movimiento(String desplazamiento) {
        int movimientoCorrecto = MOVIMIENTO_CORRECTO;

        // La función de .toUpperCase() es para que acepte mayúsculas y minúsculas
        switch (desplazamiento.toUpperCase()) {
            case "N":
                // Desplazo una posición al Norte
                posJugadorY = posJugadorY + 1;
                break;

            case "S":
                // Desplazo una posición al Sur
                posJugadorY = posJugadorY - 1;
                break;

            case "E":
                // Desplazo una posición al Este
                posJugadorX = posJugadorX + 1;
                break;

            case "O":
                // Desplazo una posición al Oeste
                posJugadorX = posJugadorX - 1;
                break;

            case "NE":
                // Desplazo una posición al Norte y otra al Este
                posJugadorY = posJugadorY + 1;
                posJugadorX = posJugadorX + 1;
                break;

            case "SE":
                // Desplazo una posición al Sur y otra al Este
                posJugadorY = posJugadorY - 1;
                posJugadorX = posJugadorX + 1;
                break;

            case "SO":
                // Desplazo una posición al Sur y otra al Oeste
                posJugadorY = posJugadorY - 1;
                posJugadorX = posJugadorX - 1;
                break;

            case "NO":
                // Desplazo una posición al Norte y otra al Oeste
                posJugadorY = posJugadorY + 1;
                posJugadorX = posJugadorX - 1;
                break;

            case "SA":
                System.out.println("Fin de la partida. ¡Gracias por jugar!");
                movimientoCorrecto = MOVIMIENTO_SALIR;
                break;
            
            default:
                System.out.println("Caracter no válido. Introduzca una movimiento válido.");
                movimientoCorrecto = MOVIMIENTO_ERRONEO;
        }
        
        if (movimientoCorrecto == MOVIMIENTO_CORRECTO) {
            controlMovimiento();
        }
        
        return movimientoCorrecto;
    }

    /**
     * Método creado para controlar el movimiento del jugador y no se salga del mapa
     */
    private void controlMovimiento() {
        if (posJugadorY > tamanioMaxY - 1) {
            posJugadorY = 0;
        }

        if (posJugadorY < 0) {
            posJugadorY = tamanioMaxY - 1;
        }

        if (posJugadorX > tamanioMaxX - 1) {
            posJugadorX = 0;
        }

        if (posJugadorX < 0) {
            posJugadorX = tamanioMaxX - 1;
        }
    }

    /**
     * Método creado para inicializar el tablero
     */
    private void inicializarTablero() {
        tablero = new String[tamanioMaxX][tamanioMaxY];
        for (int x = 0; x < tamanioMaxX; x++) { // recorre filas
            for (int y = 0; y < tamanioMaxY; y++) { // recorre columnas
                tablero[x][y] = " "; // asignamos un espacio blanco

            }
        }
    }

    /**
     * Método creado para mostrar el tablero por pantalla
     */
    public void mostrarTablero() {
        System.out.println(); // Bajamos línea
        for (int x = tamanioMaxX - 1; x >= 0; x--) { // Recorre filas en orden inverso
            System.out.print(x + " ");
            for (int y = 0; y < tamanioMaxY; y++) { // Recorre columnas normalmente
                // if para imprimir la 'J' en color rojo y si por algún casual no fuera 'J' que
                // se imprima sin color
                if (tablero[x][y] == "J") {
                    System.out.print(COLOR_RED + tablero[x][y] + COLOR_RESET);
                } else {
                    System.out.print(tablero[x][y]);
                }
                if (y < 5) {
                    System.out.print(COLOR_CYAN + " | " + COLOR_RESET);
                }
            }
            System.out.println(); // Bajamos línea
            if (x > 0) { // Evitar la última línea divisoria
                System.out.println(COLOR_CYAN + " ---+---+---+---+---+---" + COLOR_RESET);
            }
        }
        System.out.println("  0   1   2   3   4   5");
    }

    /**
     * Método creado para actualizar el tablero
     */
    public void actualizarTablero() {
        // Limpiar el tablero antes de actualizar
        for (int x = 0; x < tamanioMaxX; x++) {
            for (int y = 0; y < tamanioMaxY; y++) {
                tablero[x][y] = " "; // Espacios en blanco
            }
        }
        // Colocar 'J' en la posición actual del jugador
        tablero[posJugadorY][posJugadorX] = "J"; // Recuerda que posY es la fila y posX es la columna
    }

    /**
     * Devuelve la dirección que elije el jugador para desplazarse
     */
    public String leerMovimiento() {
        String posiblesDirecciones = "\n¿Dónde deseas desplazarte?" +
                "\nN - Norte" + "\t\t\t\t\tN\n" +
                "S - Sur" + "\t\t\t\t\t  NO   @@@   NE\n" +
                "E - Este" + "\t\t\t\t     @@   @@\n" +
                "O - Oeste" + "\t\t\t\t    @@     @@\n" +
                "NE - Noreste" + "\t\t\t\t O @@       @@ E\n" +
                "SE - Sudeste" + "\t\t\t\t    @@     @@\n" +
                "SO - Sudoeste" + "\t\t\t\t     @@   @@\n" +
                "NO - Noroeste" + "\t\t\t\t  SO   @@@   SE\n" +
                "SA - Salir" + "\t\t\t\t\tS\n";

        String direccion = Utilidades.leerEntrada(posiblesDirecciones);
        return direccion;
    }
}