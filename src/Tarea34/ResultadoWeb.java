package Tarea34;


public class ResultadoWeb {

    /**
     * Almacena todos los datos de la URL
     * @param url La URL consultada.
     * @param tiempoRespuestaMs El tiempo de respuesta en milisegundos.
     * @param tamanoContenido El tamaño del contenido en caracteres.
     */

    private final String url;
    private final long tiempoRespuestaMs;
    private final int tamanoContenido;

    //Constructor
    public ResultadoWeb(String url, long tiempoRespuestaMs, int tamanoContenido) {
        this.url = url;
        this.tiempoRespuestaMs = tiempoRespuestaMs;
        this.tamanoContenido = tamanoContenido;
    }

    // Getters
    public String getUrl() {return url;}
    public long getTiempoRespuestaMs() {return tiempoRespuestaMs;}
    public int getTamanoContenido() {return tamanoContenido;}
}