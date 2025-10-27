package R6;

public class R6 {
    public static void main(String[] args) {
        //Creacion de los hilos
        Hilo servidor1 = new Hilo("Servidor1");
        Hilo servidor2 = new Hilo("Servidor2");
        Hilo servidor3 = new Hilo("Servidor3");

        //Lanzamiento de los hilos
        servidor1.start();
        servidor2.start();
        servidor3.start();

        //Espera a q finalicen todos los hilos
        try{
            servidor1.join();
            servidor2.join();
            servidor3.join();
        }catch(InterruptedException e){
            System.out.println("Hilo interrupted");
        }
        System.out.println("Total de pedidos añadidos a los servidores: " + Hilo.totalPedidos);
    }
}
