package Tarea14;

public class Tarea14 {

    public static double capital = 1000.0;
    private static final Object lock = new Object();

    // Hilo que registra los ingresos
    static class TotalIngresos implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) { // Sirve para que se sincronice el acceso a la variable compartida
                    double saldoActual = capital;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    capital = saldoActual + 10; // suma el ingreso
                }
            }
        }
    }

    // Hilo que registra las extracciones
    static class TotalExtracciones implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3000; i++) {
                synchronized (lock) { // Sirve para que se sincronice el acceso a la variable compartida
                    double saldoActual = capital;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    capital = saldoActual - 10; // resta la extraccion
                }
            }
        }
    }


        public static void main(String[] args) {

            // Crear los hilos
            Thread ingresos = new Thread(new TotalIngresos());
            Thread extracciones = new Thread(new TotalExtracciones());

            // Iniciar los hilos
            ingresos.start();
            extracciones.start();


            try {
                // Esperar a que terminen
                ingresos.join();
                extracciones.join();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Todos los hilos han terminado.");
            System.out.println("Saldo final de la caja: " + capital);
        }
    }

