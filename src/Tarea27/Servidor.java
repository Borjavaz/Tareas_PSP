package Tarea27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6666;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado.");

            // Flujos de entrada/salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            // Bucle principal de comunicación
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente dice: " + mensaje);

                // Si el cliente dice "adios", se termina la conexión
                if (mensaje.equalsIgnoreCase("adios")) {
                    System.out.println("Cliente ha terminado la comunicación.");
                    break;
                }

                // Responder con "ECO: "
                String respuesta = "ECO: " + mensaje;
                salida.println(respuesta);
            }

            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
