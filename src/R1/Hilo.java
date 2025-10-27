package R1;

public class Hilo extends Thread {

    public Hilo(String nombre) {
        super(nombre);
    }


    public static int total = 0;
    private static final Object candado = new Object();


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (candado) {
                total++;
            }

            System.out.println(getName() + ": Entrada -> " + (i + 1));

            int tiempoEspera = 1;
            try {
            // Hilo se bloquea durante el tiempo generado
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                System.err.println(getName() + " fue interrumpido.");
            }
        }
        System.out.println(getName() + " ha terminado.");
    }
}


