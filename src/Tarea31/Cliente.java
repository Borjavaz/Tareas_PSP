package Tarea31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {

        int puerto_servidor = 6666;
        byte[] buffer = new byte[1024];

        // BufferedReader para leer desde teclado
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket datagramSocket = new DatagramSocket();
            System.out.println("Cliente conectado a " + direccionServidor.getHostAddress() + ":" + puerto_servidor);
            System.out.println("Escribe 'fin' para terminar la conversación\n");

            // Bucle de conversación infinito hasta que el usuario escriba "fin"
            while (true) {

                // El cliente escribe su mensaje
                System.out.print("Cliente: ");
                String msj = reader.readLine();

                // Verificar si el usuario quiere terminar
                if (msj.equalsIgnoreCase("fin")) {
                    System.out.println("Finalizando conversación...");
                    break;
                }
                buffer = msj.getBytes();

                DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
                datagramSocket.send(pregunta);
                System.out.println("[Cliente envió] " + msj);

                // Espera la respuesta del servidor
                DatagramPacket peticion = new DatagramPacket(new byte[1024], 1024);
                datagramSocket.receive(peticion);

                // Convierte el mensaje recibido en un String
                String msjServidor = new String(peticion.getData(), 0, peticion.getLength()).trim();
                System.out.println("[Servidor] " + msjServidor);

                // Verificar si el servidor quiere terminar
                if (msjServidor.equalsIgnoreCase("fin")) {
                    System.out.println("El servidor ha finalizado la conversación");
                    break;
                }
            }

            System.out.println("\nConversación finalizada.");
            // Cerrar el socket y el reader
            datagramSocket.close();
            reader.close();

        } catch (SocketException ex) {
            System.err.println("Error de Socket: " + ex.getMessage());
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            System.err.println("Host Desconocido: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Error de I/O: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}