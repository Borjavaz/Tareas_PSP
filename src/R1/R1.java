package R1;

public class R1 {
    public static void main(String[] args) {
// Crear  hilos
        Hilo hilo1 = new Hilo("Torno 1");
        Hilo hilo2 = new Hilo("Torno 2");
        Hilo hilo3 = new Hilo("Torno 3");
        Hilo hilo4 = new Hilo("Torno 4");

// Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

// Esperar a que los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            System.err.println("Un torno fue interrumpido.");
        }
        System.out.println("Total de aficionados: " + Hilo.total);
    }
}
