package Tarea20;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Simulación de Buzón ---");

        Buzon buzonCompartido = new Buzon();

        Thread escritor1 = new Thread(new Escritor(buzonCompartido, 1), "Escritor-1");
        Thread escritor2 = new Thread(new Escritor(buzonCompartido, 2), "Escritor-2");
        Thread lector1 = new Thread(new Lector(buzonCompartido, 1), "Lector-1");
        Thread lector2 = new Thread(new Lector(buzonCompartido, 2), "Lector-2");

        lector1.start();
        lector2.start();
        escritor1.start();
        escritor2.start();

        try {
            escritor1.join();
            escritor2.join();
            lector1.join();
            lector2.join();
        } catch (InterruptedException e) {
            System.err.println("Un hilo principal fue interrumpido.");
        }

        System.out.println("--- Todos los hilos han terminado. Fin de la simulación. ---");
    }
}