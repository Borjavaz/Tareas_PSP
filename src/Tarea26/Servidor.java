package Tarea26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    public static void main(String[] args) {
        int puerto = 6666;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado.");

            // Flujos de entrada/salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Leer 3 mensajes del cliente
            for (int i = 1; i <= 3; i++) {
                String mensaje = entrada.readLine();
                System.out.println("Cliente dice: " + mensaje);
            }

            // Enviar 3 respuestas al cliente
            for (int i = 1; i <= 3; i++) {
                String respuesta = "Respuesta " + i + " del servidor";
                salida.println(respuesta);
                System.out.println("Servidor envía: " + respuesta);
            }

            // Cerrar conexión
            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
