package Tarea34;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class ComparadorWeb {

    /**
     * Función principal para pedir las URLs, obtener los datos y realizar la comparación.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Comparador de Velocidad(ms) y Contenido Web(caracteres) ---");

        System.out.print("Introduce la primera URL (ej. https://www.google.com): ");
        String url1 = scanner.nextLine().trim();

        System.out.print("Introduce la segunda URL (ej. https://www.bing.com): ");
        String url2 = scanner.nextLine().trim();

        //cierro scanner
        scanner.close();

        //Si las url introducidas por el usuario estan vacias, mensaje de aviso
        if (url1.isEmpty() || url2.isEmpty()) {
            System.out.println("Debes introducir dos URLs válidas.");
            return;
        }

        //Almacenar los resultados de las webs
        Map<String, ResultadoWeb> resultados = new HashMap<>();

        //Procesar URL 1 usando la clase UrlConnector
        ResultadoWeb res1 = UrlConnector.obtenerDatosWeb(url1);
        if (res1 != null) {resultados.put(url1, res1);}//con el put inserta valores si no existen previos o actualiza los datos, si ya existen

        //Procesar URL 2 usando la clase UrlConnector
        ResultadoWeb res2 = UrlConnector.obtenerDatosWeb(url2);
        if (res2 != null) {resultados.put(url2, res2);} //con el put inserta valores si no existen previos o actualiza los datos, si ya existen
        // --- Comparación y Salida por Consola ---

        if (url1.equals(url2)) {
            System.out.println("No se puede comparar entre dos URLs iguales.");
        }

        if (resultados.size() < 2) {
            System.out.println("\nNo se pudieron obtener datos de ambas URLs para la comparación.");
            return;
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("RESUMEN DE RESULTADOS");
        System.out.println("=".repeat(50));

        //Determinar la web más rápida, lo hago fijando un "record" de velocidad.
        //Si alguna url tiene un tiempo de respuesta menor pasa a ser la mas rapida
        ResultadoWeb masRapida = null;
        long msRapido = Long.MAX_VALUE;

        for (ResultadoWeb res : resultados.values()) {
            if (res.getTiempoRespuestaMs() < msRapido) {
                msRapido = res.getTiempoRespuestaMs();
                masRapida = res;
            }
        }

        //Determinar la web con mas contenido, lo hago fijando un "record" de contenido.
        //Si alguna url tiene un número mayor de caracteres pasa a ser la que más contenido tiene
        ResultadoWeb masContenido = null;
        int tamanoMax = -1;

        for (ResultadoWeb res : resultados.values()) {
            if (res.getTamanoContenido() > tamanoMax) {
                tamanoMax = res.getTamanoContenido();
                masContenido = res;
            }
        }

        //print de resultados
        if (masRapida != null) {
            System.out.printf("La web más rápida ha sido: [%s] con [%d] ms.\n",
                    masRapida.getUrl(), masRapida.getTiempoRespuestaMs());
        }

        if (masContenido != null) {
            System.out.printf("La web con más contenido ha sido: [%s] con [%d] caracteres.\n",
                    masContenido.getUrl(), masContenido.getTamanoContenido());
        }

        System.out.println("=".repeat(50));
    }
}