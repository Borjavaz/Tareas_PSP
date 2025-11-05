package Tarea27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6666;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escribe mensajes (\"adios\" para salir):");

            String mensaje;
            while (true) {
                System.out.print("Tu mensaje: ");
                mensaje = sc.nextLine();

                // Enviar mensaje al servidor
                salida.println(mensaje);

                // Si el mensaje es "adios", se cierra la conexión
                if (mensaje.equalsIgnoreCase("adios")) {
                    System.out.println("Desconectando...");
                    break;
                }

                // Leer respuesta del servidor
                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    System.out.println("Servidor cerró la conexión.");
                    break;
                }
                System.out.println(respuesta);
            }

            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}