package Tarea35;

import java.util.Scanner;

public class LectorCriptomonedas {

    public static void main(String[] args) {
        Scanner escanerEntrada = new Scanner(System.in);
        ServicioCripto servicio = new ServicioCripto();
        String entradaUsuario;

        System.out.println("=== Consulta CoinLore ===");
        System.out.println("Si quiere salir, introduzca (salir)");

        // Bucle principal, se esta ejecutando mientras el mensaje del usuario no sea "salir"
        do {
            System.out.print("\nIntroduce el nombre o símbolo de la criptomoneda: ");
            entradaUsuario = escanerEntrada.nextLine().trim();

            //si el mensaje es salir, el programa sale del bucle y finaliza
            if (entradaUsuario.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            try {
                //buscar por ID
                String idMoneda = servicio.buscarIdPorNombreOSimbolo(entradaUsuario);

                //si el ID es null, mando un mensaje de moneda no encontrada
                //si encuentra el ID vuelve al princiopio para pedir otra entrada al usuario
                if (idMoneda == null) {
                    System.out.println("\nMoneda no encontrada.");
                    continue;
                }

                //Obtener objeto criptomoneda y muestro susu datos
                Criptomoneda moneda = servicio.obtenerDatosMoneda(idMoneda);

                //Si la moneda existe
                if (moneda != null) {
                    //mostrar datos de la moneda
                    mostrarDatos(moneda);
                } else {
                    System.out.println("\nMoneda no encontrada o error en la obtención de datos.");
                }

            } catch (Exception e) {
                System.err.println("\nError de conexión o procesamiento: " + e.getMessage());
            }

        } while (true); // rompo el bucle con ul break

        //Cierro scaner
        escanerEntrada.close();
    }

    /**
     * Muestra la información formateada de la moneda.
     */
    private static void mostrarDatos(Criptomoneda moneda) {

        String nombre = moneda.obtenerNombre();
        String simbolo = moneda.obtenerSimbolo();
        String precioUsd = moneda.obtenerPrecioUsd();
        String ranking = moneda.obtenerRanking();
        String porcentajeCambio24h = moneda.obtenerCambio24h();

        double valorCambio = Double.parseDouble(porcentajeCambio24h);
        String signo = valorCambio >= 0 ? "+" : "";
        String indicadorColor = valorCambio >= 0 ? "(SUBE)" : "(BAJA)";

        //print
        System.out.println("\n" + "*".repeat(50));

        System.out.println("\n=== Datos de la Criptomoneda ===");
        System.out.printf("Nombre y Símbolo: %s (%s)\n", nombre, simbolo);
        System.out.printf("Precio en USD: $%,.2f\n", Double.parseDouble(precioUsd));
        System.out.printf("Ranking: %s\n", ranking + "º");
        System.out.printf("Variación 24h: %s%s%% %s\n", signo, porcentajeCambio24h, indicadorColor);

        System.out.println("\n" + "*".repeat(50));
    }
}