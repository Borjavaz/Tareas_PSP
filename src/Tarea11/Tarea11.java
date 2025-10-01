package Tarea11;

import java.util.Random;
import java.util.Scanner;

public class Tarea11 extends Thread {

    private int nIteraciones;
    private Tarea11 siguiente;
    private int id;

    /**
     * Constructor que recibe el nombre del hilo y lo pasa
     * a la clase padre (Thread) para su inicialización.
     */
    public Tarea11(int id, int nIteraciones) {
        super("Hilo-" + id);
        this.nIteraciones = nIteraciones;
        this.id = id;

    }

    @Override
    public void run() {
        try {
            // Si no es el ultimo hilo, lanza el siguiente
            if (id < nIteraciones) {
                siguiente = new Tarea11(id + 1, nIteraciones);
                siguiente.start();
            }

            //Creo un bucle que se repite 5 veces y entra cada impresion hay un tiempo de espera de entre 100 y 600 ms
            Random random = new Random();
            for (int i = 1; i <= 5; i++) {
                System.out.println("[" + getName() + "] Iteración " + i);
                Thread.sleep(100 + random.nextInt(600));
            }

            //Esperamos a que acabe el siguiente
            if (siguiente != null) {
                siguiente.join();
            }

            // Caunado se termina el numero de iteraciones salta este mensaje por cada hilo finalizado.
            System.out.println("Acabó " + getName());

        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new Tarea11(1, 5).start();
    }
}