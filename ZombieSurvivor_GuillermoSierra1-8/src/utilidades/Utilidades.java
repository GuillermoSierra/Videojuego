package utilidades;

import java.util.Scanner;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: La clase Utilidades es para leer los datos introducidos por el usuario, lee enteros, cadena de caracteres, espacios en blanco...
 */
public class Utilidades {
    
    private static Scanner sc = new Scanner(System.in);

    /**
     * Método creado para leer cualquier carácter introducido por el usuario
     * @param textoPorPantalla Imprime el texto que hay por la pantalla
     */
    public static void pulsaTecla(String textoPorPantalla){
        try {
            System.out.println(textoPorPantalla);
            System.out.print("\nPresione " + Constantes.COLOR_GREEN + "cualquier carácter " + Constantes.COLOR_RESET + "para continuar: ");
            System.in.read();
            sc.nextLine();
        } catch (Exception errorEnterCredenciales) {
            System.out.println("No has pulsado ningún carácter. Vuelve a intentarlo.");
        }
    }

    /**
     * Método creado para leer los datos introducidos por el usuario como String 
     * @param textoPorPantalla Imprime el texto que hay por la pantalla
     * @return Devuelve el valor introducido por el usuario
     */
    public static String leerEntrada(String textoPorPantalla){
        System.out.print(textoPorPantalla);
        String valorRetorno = sc.nextLine().trim();

        return valorRetorno;
    }

    /**
     * Método creado para leer los datos introducidos por el usuario como int
     * @param textoPorPantalla Imprime el texto que hay por la pantalla
     * @param valorDesde Valor desde el que empieza
     * @param valorHasta Valor hasta el que acaba
     * @return Devuelve lo que haya introducido el usuario
     */
    public static int leerEntrada(String textoPorPantalla, int valorDesde, int valorHasta){
        System.out.print(textoPorPantalla);
        int valorRetorno = -1;
        String valorEnTexto = sc.nextLine().trim();

        if (valorEnTexto.isEmpty() || valorEnTexto.equals(" ")) {
            System.out.println("Caracter no válido. Introduzca un número del 1 - 6");
        } else {
            try {
                valorRetorno = Integer.parseInt(valorEnTexto);

                if (valorRetorno < valorDesde || valorRetorno > valorHasta) {
                    System.out.println("Número no válido. Introduzca un número entre el 1 - 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("Caracter no válido. Introduzca un número del 1 - 6");
            }
        }
        return valorRetorno;
    }

    /**
     * Método para cerrar el Scanner de manera segura.
     * Debe llamarse antes de finalizar la ejecución del programa en la main.
     */
    public static void cerrarScanner(){
        sc.close();
    }
}