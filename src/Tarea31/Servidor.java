package Tarea31;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Servidor {

    public static void main(String[] args) {
        int puerto = 6666;
        byte[] buffer = new byte[1024];

        // BufferedReader para leer desde teclado
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Servidor arrancando en puerto " + puerto);
            System.out.println("Esperando conexión de cliente...");
            System.out.println("Escribe 'fin' para terminar la conversación\n");

            DatagramSocket datagramSocket = new DatagramSocket(puerto);

            // Recibir el primer mensaje del Cliente para obtener su dirección y puerto
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(peticion);

            // Extracción de la información del cliente
            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();
            System.out.println("Cliente conectado: " + direccion.getHostAddress() + ":" + puertoCliente);

            // Convierte el primer mensaje recibido en un String
            String msjClienteInicial = new String(peticion.getData(), 0, peticion.getLength()).trim();
            System.out.println("[Cliente] " + msjClienteInicial);

            // Verificar si el cliente quiere terminar desde el primer mensaje
            if (msjClienteInicial.equalsIgnoreCase("fin")) {
                System.out.println("El cliente ha finalizado la conversación");
                datagramSocket.close();
                reader.close();
                return;
            }

            // Bucle de conversación infinito hasta que el usuario escriba "fin"
            while (true) {

                // El servidor escribe su mensaje
                System.out.print("Servidor: ");
                String msjServidor = reader.readLine();

                // Verificar si el usuario quiere terminar
                if (msjServidor.equalsIgnoreCase("fin")) {
                    System.out.println("Finalizando conversación...");

                    // Enviar mensaje de despedida al cliente
                    buffer = msjServidor.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                    datagramSocket.send(respuesta);
                    break;
                }

                buffer = msjServidor.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                datagramSocket.send(respuesta);
                System.out.println("[Servidor envió] " + msjServidor);

                // Espera respuesta del cliente
                peticion = new DatagramPacket(new byte[1024], 1024);
                datagramSocket.receive(peticion);

                // Convierte el mensaje a String
                String msjCliente = new String(peticion.getData(), 0, peticion.getLength()).trim();
                System.out.println("[Cliente] " + msjCliente);

                // Verificar si el cliente quiere terminar
                if (msjCliente.equalsIgnoreCase("fin")) {
                    System.out.println("El cliente ha finalizado la conversación");
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
        } catch (IOException ex) {
            System.err.println("Error de I/O: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}