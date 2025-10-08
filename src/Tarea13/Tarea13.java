package Tarea13;

import java.util.Random;

public class Tarea13 extends Thread {

    private Random random = new Random();

    public Tarea13(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        try {
            // Cada hilo escribe su nombre e iteración 10 veces
            for (int i = 1; i <= 10; i++) {
                System.out.println("Nombre del hilo: " + getName() + ", Iteración: " + i + ", Prioridad: " + getPriority());

                // Tiempo aleatorio entre 1 y 10 segundos (1000 a 10000 ms)
                int tiempo = 100 + random.nextInt(1000);
                Thread.sleep(tiempo);
            }

            System.out.println( getName() + " ha terminado su ejecución.");

        } catch (InterruptedException e) {
            System.out.println("Error en " + getName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Crear 4 hilos
        Tarea13 hilo1 = new Tarea13("Hilo 1");
        Tarea13 hilo2 = new Tarea13("Hilo 2");
        Tarea13 hilo3 = new Tarea13("Hilo 3");
        Tarea13 hilo4 = new Tarea13("Hilo 4");

        // Asignar prioridades
        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo4.setPriority(Thread.MAX_PRIORITY);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
    }
}

