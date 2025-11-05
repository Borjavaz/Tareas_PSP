package Tarea26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6666;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");

            // Declaro flujos de entrada/salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Enviar 3 mensajes harcodeados al server
            for (int i = 1; i <= 3; i++) {
                String mensaje = "Mensaje " + i + " del cliente";
                salida.println(mensaje);
                System.out.println("Cliente envía: " + mensaje);
            }

            // Leer 3 respuestas
            for (int i = 1; i <= 3; i++) {
                String respuesta = entrada.readLine();
                System.out.println("Servidor dice: " + respuesta);
            }

            //Cierro el Socket
            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}