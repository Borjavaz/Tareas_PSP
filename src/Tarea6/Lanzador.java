package Tarea6;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Lanzador {
    public static void ejecutarPing(String ip) {
        try {

            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if(os.contains("win")) {
                pb = new ProcessBuilder("ping", "-n", "4", ip);
            } else {
                pb = new ProcessBuilder("ping", "-c", "4", ip);
            }

            // Redirigimos la salida a la consola
            pb.inheritIO();

            // Iniciamos el proceso
            Process proceso = pb.start();

            // Esperamos a que termine y obtenemos el código de salida
            int codigoFinalizacion = proceso.waitFor();

            // Mostramos el resultado
            System.out.println("Programa ejecutado: ping " + ip);
            System.out.println("Código de finalización: " + codigoFinalizacion);


        } catch (IOException excepcion) {
            System.out.println("Error: no se pudo ejecutar el comando. " + excepcion.getMessage());
        } catch (InterruptedException excepcion) {
            System.out.println("Error en el programa: " + excepcion.getMessage());
        }
    }
}

