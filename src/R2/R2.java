package R2;

import Tarea15.Tarea15;

public class R2{

    public static void main(String[] args) {
        // Monitores de sincronización entre operarios
        Object paso1 = new Object();
        Object paso2 = new Object();

        // Creamos los 3 hilos
        Thread operario1 = new Thread(new Operario1(paso1));
        Thread operario2 = new Thread(new Operario2(paso1, paso2));
        Thread operario3 = new Thread(new Operario3(paso2));

        // Lanzamos los tres hilos
        operario1.start();
        operario2.start();
        operario3.start();

        try {
            operario3.join(); // Esperamos a que termine el último operario
        } catch (InterruptedException e) {
            System.out.println("Error: " +  e.getMessage());
        }

        System.out.println("Pieza lista para envío");
    }
}

class Operario1 implements Runnable {
    private final Object paso1;

    public Operario1(Object paso1) {
        this.paso1 = paso1;
    }

    @Override
    public void run() {
        try {
            System.out.println("Operario1: cogiendo la pieza...");
            Thread.sleep(2000); // Duerme 2 seg
            System.out.println("Operario1: pieza cogida.");
            synchronized (paso1) {
                paso1.notify(); // Avisamos a Operario2
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " +  e.getMessage());
        }
    }
}

class Operario2 implements Runnable {
    private final Object paso1;
    private final Object paso2;

    public Operario2(Object paso1, Object paso2) {
        this.paso1 = paso1;
        this.paso2 = paso2;
    }

    @Override
    public void run() {
        try {
            synchronized (paso1) {
                paso1.wait(); // Espera a que Operario1 termine
            }
            System.out.println("Operario2: pintando la pieza...");
            Thread.sleep(1000);// Duerme 1 seg
            System.out.println("Operario2: pintura terminada.");
            synchronized (paso2) {
                paso2.notify(); // Avisamos a Operario3
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " +  e.getMessage());
        }
    }
}

class Operario3 implements Runnable {
    private final Object paso2;

    public Operario3(Object paso2) {this.paso2 = paso2;}

    @Override
    public void run() {
        try {
            synchronized (paso2) {
                paso2.wait(); // Espera a que Operario2 termine
            }
            System.out.println("Operario3: embalando la pieza...");
            Thread.sleep(1000);// Duerme 1 seg
            System.out.println("Operario3: pieza embalada.");
        } catch (InterruptedException e) {
            System.out.println("Error: " +  e.getMessage());
        }
    }
}
