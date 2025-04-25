package utilidades;

import java.time.format.DateTimeFormatter;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Fichero que contiene todas las constantes para tenerlas controladas desde aqui
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

    public static final int VIDA_ENERGIA_POSITIVO = 26;
    public static final int VIDA_ENERGIA_NEGATIVO = 35;

    public static final int MAX_VIDA = 100;
    public static final int MIN_VIDA = 0;
    public static final int MAX_ENERGIA = 100;
    public static final int MIN_ENERGIA = 0;

    public static final int MOVIMIENTO_SALIR = 1;
    public static final int MOVIMIENTO_CORRECTO = 2;
    public static final int MOVIMIENTO_ERRONEO = 3;

    public static final String POSIBLES_DIRECCIONES = "\n¿Dónde deseas desplazarte?" +
                "\nN - Norte" + "\t\t\t\t\tN\n" +
                "S - Sur" + "\t\t\t\t\t  NO   @@@   NE\n" +
                "E - Este" + "\t\t\t\t     @@   @@\n" +
                "O - Oeste" + "\t\t\t\t    @@     @@\n" +
                "NE - Noreste" + "\t\t\t\t O @@       @@ E\n" +
                "SE - Sudeste" + "\t\t\t\t    @@     @@\n" +
                "SO - Sudoeste" + "\t\t\t\t     @@   @@\n" +
                "NO - Noroeste" + "\t\t\t\t  SO   @@@   SE\n" +
                "SA - Salir" + "\t\t\t\t\tS\n";
    
    public static final int COSTE_ENERGIA_POSITIVO = 19;
    public static final int COSTE_ENERGIA_NEGATIVO = 20;
    public static final int VIDA_ENERGIA_OBTENIDA = 41;
            
    public static final String[] LUGARES = {"Comisaría", "Centro comercial", 
                                            "Centro deportivo", "Cárcel", "Vertedero", "Base militar", "Puerto marítimo",
                                            "Aeropuerto", "Estación de tren", "Biblioteca", "Universidad", "Colegio",
                                            "Museo", "Teatro", "Parque", "Zoológico", "Restaurante",
                                            "Hotel", "Iglesia", "Granja", "Laboratorio", "Fábrica",
                                            "Autopista", "Cementerio", "Mirador", "Puente", "Rascacielos",
                                            "Estadio", "Gimnasio", "Plaza", "Piscina", "Casa", "Cine", "Hospital"};

    public static final String URL = "jdbc:mysql://localhost:3306/zombiesurvivor";
    public static final String USER= "root";
    public static final String PASSWORD = "Password1234";
}