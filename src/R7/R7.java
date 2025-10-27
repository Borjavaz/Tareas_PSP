package R7;

public class R7 {
    public static void main(String[] args){

        Object monitorResultado = new Object();

        Thread analista = new Thread(Analista(monitorResultado));
        Thread certificador = new Thread(Certificador(monitorResultado));

        analista.start();
        certificador.start();

        try{
            certificador.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Revision realizada correctamente");
    }
}
class Analista implements Runnable {
    private final Object monitorResultado;

    public Analista(Object monitorResultado) {
        this.monitorResultado = monitorResultado;
    }

    @Override
    public void run() {
        synchronized (monitorResultado) {
            System.out.println("Analizando pieza");

        }
    }
}