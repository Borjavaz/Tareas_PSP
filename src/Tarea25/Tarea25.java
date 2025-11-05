package Tarea25;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Tarea25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(" Escáner de Puertos");
        System.out.println("Escribe 'salir' para terminar.");

        while (true) {
            System.out.print("\nIntroduce una dirección IP o 'localhost': ");
            String host = sc.nextLine().trim();

            if (host.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.print("¿Quieres comprobar los puertos por defecto (21,22,80,443) o uno específico? (d/e): ");
            String opcion = sc.nextLine().trim().toLowerCase();

            int[] puertos = new int[0];
            if (opcion.equals("d")) {
                //Estos sol los puertos por defecto: FTP, SSH, HTTP, HTTPS
                puertos = new int[]{21, 22, 80, 443};
            }
            if (opcion.equals("e")){
                System.out.print("Introduce el puerto que quieres probar: ");
                try {
                    int puerto = Integer.parseInt(sc.nextLine().trim());
                    if (puerto < 1 || puerto > 65535) {
                        System.out.println("Puerto fuera de rango (1-65535). Intenta de nuevo.");
                        continue;
                    }
                    puertos = new int[]{puerto};
                } catch (NumberFormatException e) {
                    System.out.println("Puerto inválido. Intenta de nuevo.");
                    continue;
                }
            }
            
            System.out.println("\nEscaneando " + host + "...\n");

            for (int puerto : puertos) {
                // Usamos timeout para que no espere indefinidamente
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, puerto), 2000); // timeout 2 seg
                    System.out.println("Puerto " + puerto + " ABIERTO");
                } catch (UnknownHostException e) {
                    System.out.println("Host desconocido: " + host);
                    break;
                } catch (IOException e) {
                    System.out.println("Puerto " + puerto + " CERRADO");
                }
            }
        }
        sc.close();
    }
}
