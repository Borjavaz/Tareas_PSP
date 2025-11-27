package Tarea30;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente{

    public static void main(String[] args) {

        int puerto_servidor = 6666;
        byte[] buffer = new byte[1024];

        // Mensajes que el cliente enviará
        String[] mensajesCliente = {
                "Mensaje 1: sin tv",
                "Mensaje 2: y sin cerveza",
                "Mensaje 3: Homer"
        };

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket datagramSocket = new DatagramSocket();
            System.out.println("Cliente conectado a " + direccionServidor.getHostAddress() + ":" + puerto_servidor);

            // Bucle de conversación 3 iteraciobnes
            for (int i = 0; i < 3; i++) {

                // El cliente envía su mensaje i
                String msj = mensajesCliente[i];
                buffer = msj.getBytes();

                DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
                datagramSocket.send(pregunta);
                System.out.println("[Cliente] " + msj);


                // espera la respuesta del servidor.
                if (i < 3) {

                    // Se reusa el buffer para recibir la respuesta del servidor
                    DatagramPacket peticion = new DatagramPacket(new byte[1024], 1024);
                    datagramSocket.receive(peticion);

                    // Convierte el mensaje recibido en un String
                    String msjServidor = new String(peticion.getData(), 0, peticion.getLength()).trim();
                    System.out.println("[Servidor] " + msjServidor);
                }
            }

            System.out.println("\nConversación finalizada.");
            // Cerrar el socket
            datagramSocket.close();

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