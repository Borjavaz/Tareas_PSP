package Tarea12;

public class Tarea12 extends Thread {

    public static void main(String[] args) {
        // Crear los tres hilos
        Thread pares = new Thread(new SumaPares());
        Thread impares = new Thread(new SumaImpares());
        Thread tresCuatro = new Thread(new SumaTresCuatro());

        // Iniciar los hilos
        pares.start();
        impares.start();
        tresCuatro.start();

        // Esperar a que terminen
        try {
            pares.join();
            impares.join();
            tresCuatro.join();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Todos los hilos han terminado.");
    }
}

// Hilo que suma los números pares
class SumaPares implements Runnable {
    @Override
    public void run() {
        long suma = 0;
        for (int i = 2; i <= 1923; i += 2) {
            suma += i;
        }
        System.out.println("Suma de pares: " + suma);
    }
}

// Hilo que suma los números impares
class SumaImpares implements Runnable {
    @Override
    public void run() {
        long suma = 0;
        for (int i = 1; i <= 1923; i += 2) {
            suma += i;
        }
        System.out.println("Suma de impares: " + suma);
    }
}

// Hilo que suma los números que terminan en 3 o 4
class SumaTresCuatro implements Runnable {
    @Override
    public void run() {
        long suma = 0;
        for (int i = 1; i <= 1923; i++) {
            int ultimo = i % 10;
            if (ultimo == 3 || ultimo == 4) {
                suma += i;
            }
        }
        System.out.println("Suma de números que terminan en 3 o 4: " + suma);
    }
}
