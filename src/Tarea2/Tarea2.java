package Tarea2;

public class Tarea2 {
    /*
        public static void main(String[] args) throws IOException {
            String [] procesoAEjecutar = {"gnome-text-editor","prueba.txt"};
            Process P = Runtime.getRuntime().exec(procesoAEjecutar);

            tarea2(args);
        }
    */
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        long MB = 1024 * 1024;

        System.out.println("Memoria total: " + rt.totalMemory() / MB + " MiB");
        System.out.println("Memoria libre: " + rt.freeMemory() / MB + " MiB");
        System.out.println("Memoria en uso: " + (rt.totalMemory() - rt.freeMemory()) / MB + " MiB");
        System.out.println("Procesadores disponibles: " + rt.availableProcessors());

        System.out.println("\n Propiedades de la clase System");
        System.getProperties().list(System.out);
    }
}
