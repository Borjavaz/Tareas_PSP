package Tarea34;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UrlConnector {

    /**
     * Realiza una petición GET a la URL, mide el tiempo de respuesta y obtiene el tamaño del contenido.
     * @param urlString La URL a consultar.
     * @return Un objeto ResultadoWeb con los datos, o null en caso de error.
     */
    public static ResultadoWeb obtenerDatosWeb(String urlString) {

        System.out.println("\n" + "*".repeat(50));

        System.out.println("Procesando " + urlString + "...");
        HttpURLConnection connection = null;
        try {
            //convierto la direccion web en un objeto
            URL url = new URL(urlString);
            //abro la conexion con el servidor web
            connection = (HttpURLConnection) url.openConnection();
            //defino la petición, le digo al servidor que quiero hacer un get (obtener)
            connection.setRequestMethod("GET");
            //timeout
            connection.setConnectTimeout(10000); // 10 s para la conexión
            connection.setReadTimeout(10000);    // 10 s para la lectura

            //inicio de un "cronometro"
            long startTime = System.currentTimeMillis();

            // conectar y esperar respuesta
            int responseCode = connection.getResponseCode();
            //detener cronometro
            long endTime = System.currentTimeMillis();

            //calculo la diferencia
            long tiempoRespuestaMs = endTime - startTime;

            //compruebo si el servidor devolvio un codigo 200, si devuelve uno como 400, retorna un null
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Error: Código de respuesta HTTP: " + responseCode);
                return null;
            }

            //preparar la lectura y contar caracteres
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;

            //Leer linea por linea y acumular el contenido
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            //cierro el lector
            reader.close();

            //cuento los caracteres
            int tamanoContenido = content.length();

            //print del resultado
            System.out.printf("Datos obtenidos: Tiempo=%d ms, Tamaño=%d caracteres.\n",
                    tiempoRespuestaMs, tamanoContenido);

            return new ResultadoWeb(urlString, tiempoRespuestaMs, tamanoContenido);

            //manejo excepciones
        } catch (IOException e) {
            System.out.println("Error al acceder a " + urlString + ": " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
