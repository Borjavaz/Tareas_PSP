package R5;

import java.util.Random;

public class R5 {
    public static void main(String[] args) {

        Object buzon = new Object();

        Thread cliente = new Thread(new Cliente(buzon));
        Thread repartidor = new Thread(new Repartidor(buzon));

        cliente.start();
        repartidor.start();

        try {
            cliente.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("El paquete a sido entregado con exito");
    }
}
class Cliente implements Runnable{
    private Object buzon;
    public Cliente(Object buzon) {
        this.buzon = buzon;
    }
    @Override
    public void run() {
        try {
            System.out.println("Cliente esperando paquete");
            synchronized (buzon) {
                buzon.wait();
            }
            System.out.println("Cliente recibió el paquete");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Repartidor implements Runnable{

    private Object buzon;
    public Repartidor(Object buzon) {
        this.buzon = buzon;
    }
    @Override
    public void run() {
        Random random = new Random();
        // Generar un tiempo de espera aleatorio entre 1 y 5 segundos, para simular la espera de entrega
        int tiempoEspera = 1000 + random.nextInt(5000);

        try {
            System.out.println("Paquete en camino");
            Thread.sleep(tiempoEspera);
            System.out.println("Paquete entregado");
            synchronized (buzon) {
                buzon.notify();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
