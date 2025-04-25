package bbdd;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.ResultSet;

import zombiesurvivor.Partida;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase que gestiona la conexión con la base de datos MySQL utilizada en el videojuego ZombieSurvivor.
 * 
 * Permite:
 * - Establecer y cerrar la conexión con la base de datos.
 * - Guardar nuevas partidas en la tabla {@code partidas} mediante sentencias preparadas.
 * - Recuperar todas las partidas almacenadas para ser consultadas desde la aplicación.
 * 
 * Utiliza JDBC para la conexión y ejecución de sentencias SQL, y trabaja con objetos {@code Partida}
 * para intercambiar datos entre la lógica del juego y la base de datos.
 */ 
public class ConexionBBDD {
    private final String URL = "jdbc:mysql://localhost:3306/zombiesurvivor";
    private final String USER= "root";
    private final String PASSWORD = "Password1234";
    private Connection cnx;

    /**
     * Constructor de la clase {@code ConexionBBDD}.
     * 
     * Establece la conexión con la base de datos MySQL utilizando los parámetros definidos
     * (URL, usuario y contraseña). Si la conexión falla, se captura y muestra el mensaje de error correspondiente.
     */
    public ConexionBBDD() {
        try {
            this.cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cierra la conexión actual con la base de datos.
     * 
     * Este método se utiliza para liberar los recursos asociados a la conexión
     * una vez que ya no se necesite. Si la conexión ya está cerrada o se produce
     * un error al cerrarla, se captura la excepción y se muestra el mensaje de error.
     */
    public void desconexionBD(){
        try {
            cnx.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Guarda los datos de una partida en la base de datos.
     * 
     * Este método inserta un nuevo registro en la tabla {@code partidas} con los datos de la partida pasada como parámetro:
     * nombre del jugador, fecha de inicio,
     * fecha de fin, duración en segundos, número de movimientos realizados y si ganó o no la partida.
     * Utiliza una sentencia preparada para evitar inyecciones SQL y para asegurar la correcta inserción de los datos.
     * 
     * @param partida Objeto {@code Partida} que contiene los datos a guardar en la base de datos.
     */
    public void guardaPartidaBBDD(Partida partida){
        String textoInsert =
        "INSERT INTO partidas (nombre_jugador, fecha_inicio, fecha_fin, duracion_sec, num_movimiento, victoria) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement sentenciaInsert = cnx.prepareStatement(textoInsert);

            sentenciaInsert.setString(1, partida.getNombre());
            sentenciaInsert.setTimestamp(2, Timestamp.valueOf(partida.getFechaInicio()));
            sentenciaInsert.setTimestamp(3, Timestamp.valueOf(partida.getFechaFin()));
            sentenciaInsert.setLong(4, partida.getDuracionSegundos());
            sentenciaInsert.setInt(5, partida.getNumMovimiento());
            sentenciaInsert.setBoolean(6, partida.getVictoria());

            sentenciaInsert.executeUpdate(); // Esta línea ejecuta la sentencia insert con los valores pasados por parametros

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Recupera todas las partidas almacenadas en la base de datos y las devuelve en una lista.
     * 
     * - Ejecuta una consulta SQL sobre la tabla {@code partidas}, ordenando los resultados por fecha de inicio.
     * - Por cada fila obtenida, crea un objeto {@code Partida} con sus datos correspondientes:
     *   nombre del jugador, fecha de inicio y fin, duración en segundos, número de movimientos y resultado.
     * - Si ocurre un error durante la ejecución de la consulta, se imprime el mensaje del error por consola.
     * 
     * @return Una lista ({@code ArrayList}) de objetos {@code Partida} con los datos cargados desde la base de datos.
     */
    public ArrayList<Partida> getPartidasBBDD(){
        ArrayList<Partida> listaPartidas = new ArrayList<>();

        String textoSelect =
        "SELECT nombre_jugador, fecha_inicio, fecha_fin, duracion_sec, num_movimiento, victoria FROM partidas " +
        "ORDER BY fecha_inicio ASC";
        
        try {
            PreparedStatement sentenciaSelect = cnx.prepareStatement(textoSelect);

            ResultSet filasBBDD = sentenciaSelect.executeQuery();

            while (filasBBDD.next()) {
                Partida partida = new Partida(filasBBDD.getString("nombre_jugador"));
                
                Timestamp timeStampInicio = filasBBDD.getTimestamp("fecha_inicio");
                LocalDateTime fechaInicio = timeStampInicio.toLocalDateTime();
                partida.setFechaInicio(fechaInicio);

                Timestamp timeStampFin = filasBBDD.getTimestamp("fecha_fin");
                LocalDateTime fechaFin = timeStampFin.toLocalDateTime();
                partida.setFechaFin(fechaFin);

                partida.setDuracionSec(filasBBDD.getLong("duracion_sec"));

                partida.setNumMovimiento(filasBBDD.getInt("num_movimiento"));

                partida.setVictoria(filasBBDD.getBoolean("victoria"));

                listaPartidas.add(partida);
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaPartidas;
    }
}