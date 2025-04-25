package utilidades;

import java.time.format.DateTimeFormatter;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: 
 */
public class Constantes {
    public static final String COLOR_RESET = "\u001B[0m";

    public static final String COLOR_BLACK = "\u001B[30m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_WHITE = "\u001B[37m";

    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    public static final String STYLE_BOLD = "\u001B[1m";
    public static final String STYLE_UNDERLINED = "\u001B[4m";
    public static final String STYLE_REVERSED = "\u001B[7m";

    public static final int FINALIZARPROGRAMA = 6;

    public static final String TITULO = Constantes.COLOR_RED
                + "███████╗ ██████╗ ███╗   ███╗██████╗ ██╗███████╗███████╗██╗   ██╗██████╗ ██╗   ██╗██╗██╗   ██╗ ██████╗ ██████╗ \n" +
                "╚══███╔╝██╔═══██╗████╗ ████║██╔══██╗██║██╔════╝██╔════╝██║   ██║██╔══██╗██║   ██║██║██║   ██║██╔═══██╗██╔══██╗\n" +
                "  ███╔╝ ██║   ██║██╔████╔██║██████╔╝██║█████╗  ███████╗██║   ██║██████╔╝██║   ██║██║██║   ██║██║   ██║██████╔╝\n" +
                " ███╔╝  ██║   ██║██║╚██╔╝██║██╔══██╗██║██╔══╝  ╚════██║██║   ██║██╔══██╗╚██╗ ██╔╝██║╚██╗ ██╔╝██║   ██║██╔══██╗\n" +
                "███████╗╚██████╔╝██║ ╚═╝ ██║██████╔╝██║███████╗███████║╚██████╔╝██║  ██║ ╚████╔╝ ██║ ╚████╔╝ ╚██████╔╝██║  ██║\n" +
                "╚══════╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═══╝   ╚═════╝ ╚═╝  ╚═╝\n"
                + Constantes.COLOR_RESET + "";

    public static final String NORMAS = COLOR_RED + "- " + COLOR_RESET
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
    
    public static final String CREDENCIALES = "\nVideojuego creado por Guillermo Sierra Castejón, fecha de inicio 27/12/2024 ";

    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}