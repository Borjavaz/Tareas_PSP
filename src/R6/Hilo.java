package R6;

import java.util.Random;

public class Hilo extends Thread{

    public Hilo(String nombre){super(nombre);}

    public static int totalPedidos = 0;
    private static final Object candado = new Object();

    @Override
    public void run() {
        for(int i = 0; i < 1500; i++){
            synchronized (candado){
                totalPedidos ++;
            }
            System.out.println(getName() + " pedido -> " + (i+1));

            Random random = new Random();
            // Generar un tiempo de espera aleatorio entre 1 y 5 mili segundos
            int tiempoEspera = 1 + random.nextInt(5);
            try {
                Thread.sleep(tiempoEspera);
            }catch (InterruptedException e){
                System.out.println("Error en un pedido al servidor");
            }
        }
        System.out.println(getName() + " terminó");
    }
}
