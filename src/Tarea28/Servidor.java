package Tarea28;

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
            System.out.println("Servidor multicliente escuchando en el puerto " + puerto + "...");

            // Bucle infinito para aceptar múltiples clientes
            while (true) {
                // Bloquea hasta que llega una nueva conexión de cliente
                Socket socketCliente = servidor.accept();

                // Crea un nuevo hilo que ejecuta la lógica de ManejadorCliente
                Thread hiloCliente = new Thread(new ManejadorCliente(socketCliente));
                hiloCliente.start(); // El nuevo hilo comienza a ejecutar run()
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket socketCliente;
        private static int contadorClientes = 0;
        private final int idCliente;

        public ManejadorCliente(Socket socket) {
            this.socketCliente = socket;
            this.idCliente = ++contadorClientes;
            System.out.println("Cliente #" + idCliente + " conectado desde " + socket.getRemoteSocketAddress());
        }

        @Override
        public void run() {
            try (
                    // Flujos de entrada/salida del cliente
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
            ) {
                String mensaje;

                // Bucle principal de comunicación con cliente
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("Cliente #" + idCliente + " dice: " + mensaje);

                    if (mensaje.equalsIgnoreCase("adios")) {
                        System.out.println("Cliente #" + idCliente + " ha terminado la comunicación.");
                        break;
                    }

                    // Responder con "ECO: "
                    String respuesta = "ECO: " + mensaje;
                    salida.println(respuesta);
                }

            } catch (IOException e) {
                System.err.println("Error de comunicación con Cliente #" + idCliente + ": " + e.getMessage());
            } finally {
                try {
                    // Cierro el socket
                    socketCliente.close();
                    System.out.println("Conexión con Cliente #" + idCliente + " cerrada.");
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket de Cliente #" + idCliente + ": " + e.getMessage());
                }
            }
        }
    }
}