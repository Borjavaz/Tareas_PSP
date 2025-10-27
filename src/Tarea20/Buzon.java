package Tarea20;

import java.util.concurrent.ThreadLocalRandom;

public class Buzon {
    private String mensaje;

    public Buzon() {
        this.mensaje = null;
    }

    public synchronized String leer() {
        while (mensaje == null) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Buzón vacío. Esperando mensaje...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String mensajeLeido = this.mensaje;
        this.mensaje = null;

        System.out.println(Thread.currentThread().getName() + " **LEYÓ** el mensaje: [" + mensajeLeido + "]");

        notifyAll();

        return mensajeLeido;
    }

    public synchronized void escribir(String nuevoMensaje) {
        while (mensaje != null) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Buzón lleno. Esperando que sea leído...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.mensaje = nuevoMensaje;

        System.out.println(Thread.currentThread().getName() + " **ESCRIBIÓ** el mensaje: [" + nuevoMensaje + "]");

        notifyAll();
    }
}

class Escritor implements Runnable {
    private final Buzon buzon;
    private final int id;

    public Escritor(Buzon buzon, int id) {
        this.buzon = buzon;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            String mensajeAEnviar = "Mensaje E-" + id + " (Nº " + i + ")";
            buzon.escribir(mensajeAEnviar);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Escritor " + id + " ha finalizado su tarea.");
    }
}

class Lector implements Runnable {
    private final Buzon buzon;
    private final int id;

    public Lector(Buzon buzon, int id) {
        this.buzon = buzon;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            String mensaje = buzon.leer();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Lector " + id + " ha finalizado su tarea.");
    }
}