package Tarea10;

import java.util.Random;

class Hilo extends Thread {

    public Hilo(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": Iteración " + (i + 1));

// Generar un tiempo de espera aleatorio entre 1 y 10 segundos
            int tiempoEspera = 1000 + random.nextInt(10000);
            try {
// Hilo se bloquea durante el tiempo generado
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                System.err.println(getName() + " fue interrumpido.");
            }
        }
    }
}

public class Tarea10 {
    public static void main(String[] args) {
// Crear dos hilos
        Hilo hilo1 = new Hilo("Hilo 1");
        Hilo hilo2 = new Hilo("Hilo 2");

// Iniciar los hilos
        hilo1.start();
        hilo2.start();

// Esperar a que ambos hilos terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.err.println("El hilo principal fue interrumpido.");
        }

        System.out.println("Ejemplo de hilos finalizado.");
    }
}



