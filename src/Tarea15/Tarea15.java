package Tarea15;

public class Tarea15 extends Thread {

    public Tarea15(String nombre) {
        super(nombre);
    }


    @Override
    public void run() {
        try {
            // Cada hilo escribe su nombre e iteración 8 veces
            for (int i = 1; i <= 8; i++) {
                System.out.println("Nombre del hilo: " + getName() + ", Iteración: " + i);
                Thread.sleep(100);
            }
            System.out.println( getName() + " ha terminado su ejecución.");
        } catch (InterruptedException e) {
            System.out.println("Error en " + getName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Tarea15 hilo1 = new Tarea15("Hilo 1");
        Tarea15 hilo2 = new Tarea15("Hilo 2");
        Tarea15 hilo3 = new Tarea15("Hilo 3");

       try{
        hilo3.start();
        hilo3.join();

        hilo2.start();
        hilo2.join();

        hilo1.start();
        hilo1.join();

    }catch(InterruptedException e){
           System.out.println("Error: " +  e.getMessage());
       }
    }
}
