package Tarea5;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tarea5 {
    public static void main(String[] args) {

        //Iniciamos el bucle para que se ejecute todo el rato
        while (true) {
            Scanner sc = new Scanner(System.in);

            //Le pedimos al usuario el comando y sus parametros y lo guardamos en una variable
            System.out.println("Ingrese un comando y sus parametros (si quiere salir ponga salir): ");
            String entrada = sc.nextLine();

            //Condicional para salir del programa si el usuario pone salir, tambien se cierra el scaner y devuelve como codigo de salida un 0
            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa");
                sc.close();
                System.exit(0);
            }

            //Divido el mensage del usuario en: comando + parámetros
            String[] partes = entrada.split(" ");
            try {
                //Inicio el proceso
                ProcessBuilder pb = new ProcessBuilder(partes);
                Process proceso = pb.start();

                // Esperamos a que termine y obtenemos el código de salida
                int codigoFinalizacion = proceso.waitFor();

                //Muestro el resultado
                System.out.println("Programa ejecutado: " + partes[0]);
                System.out.println("Código de finalización: " + codigoFinalizacion);

            } catch (IOException excepcion) {
                System.out.println("Error: no se pudo ejecutar el comando. " + excepcion.getMessage());
            }catch (InterruptedException excepcion){
                System.out.println("Error en el programa: " + excepcion.getMessage());
            }
        }
    }
}
