package Tarea32;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    private static final int PUERTO = 6666;
    private static final int TAMANO_BUFFER = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socketUDP = new DatagramSocket(PUERTO)) {
            System.out.println("Servidorv iniciado y escuchando en el puerto " + PUERTO);

            byte[] bufferEntrada = new byte[TAMANO_BUFFER];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

            while (true) {
                //Recibe el paquete del cliente
                socketUDP.receive(paqueteEntrada);

                String mensajeCliente = new String(
                        paqueteEntrada.getData(),
                        paqueteEntrada.getOffset(),
                        paqueteEntrada.getLength()
                ).trim();

                InetAddress direccionCliente = paqueteEntrada.getAddress();
                int puertoCliente = paqueteEntrada.getPort();

                System.out.println("Mensaje recibido de " + direccionCliente.getHostAddress() + ":" + puertoCliente + ": " + mensajeCliente);

                if (mensajeCliente.equalsIgnoreCase("adios") || mensajeCliente.equalsIgnoreCase("adiós")) {
                    System.out.println("Cliente indica 'adios'. El servidor sigue esperando peticiones.");
                    continue;
                }

                // Declaro una variable quue es la respuesta que procesa(comprueba la palabra más larga) el mensaje del cliente.
                String respuesta = procesarMensaje(mensajeCliente);

                //Enviar la respuesta al cliente
                byte[] bufferSalida = respuesta.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        bufferSalida,
                        bufferSalida.length,
                        direccionCliente,
                        puertoCliente
                );
                socketUDP.send(paqueteSalida);

                System.out.println("Respuesta enviada: " + respuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procesa una cadena de palabras para encontrar la más larga.
     */
    private static String procesarMensaje(String mensaje) {
        // Divide la cadena por espacios, comas o barras.
        String[] palabras = mensaje.split("[ ,/]+");

        String masLarga = "";
        for (String p : palabras) {
            // Me aseguro de que no se cuentan cadenas vacias
            if (!p.isEmpty() && p.length() > masLarga.length()) {
                masLarga = p;
            }
        }

        //Si la palabra esta vacia print de que no se encontró ninguna palabra
        //Devuelve la palabra mas larga y la longitud de esta

        if (masLarga.isEmpty()) {
            return "No se encontraron palabras válidas.";
        }

        return masLarga + " (longitud: " + masLarga.length() + ")";
    }
}