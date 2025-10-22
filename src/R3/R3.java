package R3;

import java.util.Random;

public class R3 {
    public static void main(String[] args) {

        Object buzon = new Object();

        Thread cliente = new Thread(new Cliente(buzon));
        Thread repartidor = new Thread(new Repartidor(buzon));

        cliente.start();
        repartidor.start();

        try {
            cliente.join();//Esperamos a que el cliente tenga el paquete
        }catch (InterruptedException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static class Cliente implements Runnable{
        private Object buzon;

        public Cliente(Object buzon) {this.buzon = buzon;}

        @Override
        public void run() {
            try {
                synchronized (buzon) {
                    System.out.println("Cliente esperando el paquete...");
                    buzon.wait();
                    System.out.println("Cliente recogiendo el paquete");
                }
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static class Repartidor implements Runnable{

        private Object buzon;

        public Repartidor(Object buzon) {this.buzon = buzon;}

        @Override
        public void run() {
            Random random = new Random();
            // Generar un tiempo de espera aleatorio entre 1 y 5 segundos, para simular la espera de entrega
            int tiempoEspera = 1000 + random.nextInt(5000);
            try {
                Thread.sleep(tiempoEspera);
                synchronized (buzon) {
                    System.out.println("Repartidor entregando el paquete...");
                    buzon.notify();//Notifica al cliente, qu eel paquete ya se esntregó
                }
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
