package Tarea32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    private static final int PUERTO_SERVIDOR = 6666;
    private static final String IP_SERVIDOR = "localhost";
    private static final int TAMANO_BUFFER = 1024;

    public static void main(String[] args) {
        try (
                // Creamos un DatagramSocket para enviar y recibir paquetes
                DatagramSocket socketUDP = new DatagramSocket();
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // print al ejecutar el cliente
            InetAddress direccionServidor = InetAddress.getByName(IP_SERVIDOR);
            System.out.println("Cliente iniciado.");
            System.out.println("Introduce una lista de palabras (o 'adios' para terminar):");

            String mensajeUsuario;
            while (true) {
                System.out.print("Cliente: ");
                mensajeUsuario = teclado.readLine();

                //Enviar mensaje al servidor
                byte[] bufferSalida = mensajeUsuario.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        bufferSalida,
                        bufferSalida.length,
                        direccionServidor,
                        PUERTO_SERVIDOR
                );
                socketUDP.send(paqueteSalida);

                // Si el cliente introduce adios(con tilde, con mayusc...) el programa se cierra
                if (mensajeUsuario.equalsIgnoreCase("adios") || mensajeUsuario.equalsIgnoreCase("adiós")) {
                    System.out.println("Finalizando conexión...");
                    break;
                }

                // Esperar a recibir la respuesta del servidor
                byte[] bufferEntrada = new byte[TAMANO_BUFFER];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                socketUDP.receive(paqueteEntrada);

                String respuestaServidor = new String(
                        paqueteEntrada.getData(),
                        paqueteEntrada.getOffset(),
                        paqueteEntrada.getLength()
                );

                System.out.println("Servidor: " + respuestaServidor.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}