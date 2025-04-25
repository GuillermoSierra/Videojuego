package zombiesurvivor;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * FECHA: 28/04/2025
 * @author: Guillermo Sierra Castejón
 * @version: 1.8
 * DESCRIPCIÓN: Clase que representa una partida del videojuego ZombieSurvivor. 
 * Contiene toda la información relacionada con una sesión de juego, como el nombre del jugador,
 * la fecha y hora de inicio y fin de la partida, el número de movimientos realizados, 
 * si se ha ganado o perdido, y la duración de la partida tanto en segundos como en formato legible.
 * 
 * También proporciona métodos para gestionar el estado de la partida (iniciar, finalizar, marcar como ganada),
 * calcular su duración y devolver formatos legibles para mostrar la información en pantalla o guardarla.
 */
public class Partida {
    private String nombre;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int numMovimiento;
    private boolean victoria; // 1 Victoria 0 Derrota
    private long duracionSec;
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor de la clase Partida. Inicializa una nueva partida con el nombre dado,
     * establece la fecha de inicio en el momento actual y marca la partida como no ganada por defecto.
     * @param nombre Nombre de la partida o del jugador asociado a la partida.
     */
    public Partida(String nombre){
        this.nombre = nombre;
        this.fechaInicio = LocalDateTime.now();
        this.victoria = false; // Por defecto la partida está en derrota
    }

    /**
     * Devuelve el nombre del jugador asociado a la partida.
     * @return Nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la partida.
     * @param nombre Nuevo nombre de la partida.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el número de movimientos realizados en la partida.
     * @return Número de movimientos realizados.
     */
    public int getNumMovimiento() {
        return numMovimiento;
    }

    /**
     * Establece un nuevo valor para el número de movimientos realizados en la partida.
     * @param numMovimiento Número total de movimientos.
     */
    public void setNumMovimiento(int numMovimiento) {
        this.numMovimiento = numMovimiento;
    }

    /**
     * Devuelve si la partida fue ganada o no.
     * @return true si la partida fue ganada, false si fue perdida.
     */
    public boolean getVictoria() {
        return victoria;
    }

    /**
     * Establece el estado de victoria de la partida.
     * @param victoria true si la partida fue ganada, false si fue perdida.
     */
    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }

    /**
     * Devuelve la duración de la partida en segundos.
     * 
     * @return duración total de la partida en segundos.
     */
    public long getDuracionSec() {
        return duracionSec;
    }

    /**
     * Establece la duración de la partida en segundos.
     * 
     * @param duracionSec duración total que se desea asignar a la partida, en segundos.
     */
    public void setDuracionSec(long duracionSec) {
        this.duracionSec = duracionSec;
    }

    /**
     * Marca el final de la partida estableciendo la fecha y hora actuales como el momento de finalización.
     */
    public void terminarPartida(){
        this.fechaFin = LocalDateTime.now();
    }

    /**
     * Incrementa en 1 el contador de movimientos realizados en la partida.
     */
    public void incrementarMovimiento(){
        this.numMovimiento += 1;
    }

    /**
     * Indica que la partida ha sido ganada, cambiando el estado de victoria a true.
     */
    public void partidaGanada(){
        this.victoria = true;
    }

    /**
     * Devuelve la fecha y hora en la que comenzó la partida.
     * 
     * @return un objeto {@code LocalDateTime} que representa la fecha y hora de inicio de la partida.
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha y hora de inicio de la partida.
     * 
     * @param fechaInicio fecha y hora en que comienza la partida (objeto {@code LocalDateTime}).
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Establece la fecha y hora de finalización de la partida.
     * 
     * @param fechaFin fecha y hora en que finaliza la partida (objeto {@code LocalDateTime}).
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * Devuelve la fecha y hora en la que terminó la partida.
     * 
     * @return un objeto {@code LocalDateTime} que representa la fecha y hora de finalización de la partida.
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    /**
     * Devuelve la hora de inicio de la partida formateada en formato HH:mm:ss.
     * Si la fecha de inicio no está definida, devuelve "N/A".
     * @return Hora de inicio como cadena en formato HH:mm:ss o "N/A" si no está disponible.
     */
    public String getHoraInicioFormateada() {
        return (fechaInicio != null) ? fechaInicio.format(FORMATO_HORA) : "N/A";
    }
    
    /**
     * Devuelve la hora de finalización de la partida formateada en formato HH:mm:ss.
     * Si la fecha de fin no está definida, devuelve "En curso".
     * @return Hora de fin como cadena en formato HH:mm:ss o "En curso" si aún no ha finalizado.
     */
    public String getHoraFinFormateada() {
        return (fechaFin != null) ? fechaFin.format(FORMATO_HORA) : "En curso";
    }

    /**
     * Devuelve la duración de la partida en formato hh:mm:ss.
     * @return String con duración formateada.
     */
    public String getDuracionFormateada() {
        if (fechaInicio != null && fechaFin != null) {
            Duration duracion = Duration.between(fechaInicio, fechaFin);
            long horas = duracion.toHours();
            long minutos = duracion.toMinutes() % 60;
            long segundos = duracion.getSeconds() % 60;

            return String.format("%02d:%02d:%02d", horas, minutos, segundos);
        }
        return "No disponible";
    }

    /**
     * Calcula la duración total de la partida en segundos, tomando como referencia la diferencia entre la fecha de inicio y la fecha de fin.
     * 
     * @return el número total de segundos que duró la partida. Devuelve 0 si alguna de las fechas es nula.
     */
    public long getDuracionSegundos() {
        long segundos = 0;
        if (fechaInicio != null && fechaFin != null) {
            Duration duracion = Duration.between(fechaInicio, fechaFin);
            
            segundos = duracion.getSeconds();
        }
        return segundos;
    }
}