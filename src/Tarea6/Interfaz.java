package Tarea6;

import java.util.Scanner;

public class Interfaz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bucle principal para que se ejecute hasta que el usuario introduzca "salir"
        while (true) {
            System.out.println("Ingrese la IP/host sobre lo que quiere hacer ping (o 'salir' para terminar): ");
            String ip = sc.nextLine();

            // Condicional para salir
            if (ip.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                sc.close();
                System.exit(0);
            }

            // Llamamos a Lanzador
            Lanzador.ejecutarPing(ip);
        }
    }
}
