package Tarea30;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        int puerto = 6666;
        byte[] buffer = new byte[1024];

        // Mensajes que el servidor enviará
        String[] mensajesServidor = {
                "Mensaje 1: Pierde",
                "Mensaje 2: La",
                "Mensaje 3: Cabeza"
        };

        try {
            System.out.println("Servidor arrancando en puerto " + puerto);

            DatagramSocket datagramSocket = new DatagramSocket(puerto);

            // Recibir el primer mensaje del Cliente para obtener su dirección y puerto
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(peticion);

            // Extracción de la información del cliente
            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();
            System.out.println("Cliente encontrado: " + direccion.getHostAddress() + ":" + puertoCliente);

            // Convierte el primer mensaje recibido en un String
            String msjClienteInicial = new String(peticion.getData(), 0, peticion.getLength()).trim();
            System.out.println("\n[Cliente] " + msjClienteInicial);


            //Bucle de conversación 3 iteraciones
            for (int i = 0; i < 3; i++) {


                // El servidor envía su mensaje i
                String msjServidor = mensajesServidor[i];
                buffer = msjServidor.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                datagramSocket.send(respuesta);
                System.out.println("[Servidor] " + msjServidor);

                // Si no es la última iteración, espera respuesta del cliente
                if (i < 2) {

                    // Se reusa el buffer para recibir el siguiente mensaje del cliente
                    peticion = new DatagramPacket(new byte[1024], 1024);
                    datagramSocket.receive(peticion);

                    // Convierte el mensaje a String
                    String msjCliente = new String(peticion.getData(), 0, peticion.getLength()).trim();
                    System.out.println("[Cliente] " + msjCliente);
                }
            }

            System.out.println("\nConversación finalizada.");
            // Cerrar el socket al finalizar
            datagramSocket.close();

        } catch (SocketException ex) {
            System.err.println("Error de Socket: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Error de I/O: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}