package Carrera;

import java.util.Random;

public class Carrera {
    private static final int META = 70;
    private static int posicionTortuga = 1;
    private static int posicionLiebre = 1;
    static volatile boolean carreraTerminada = false;
    private static final Object LOCK = new Object();

    public static int getPosicionTortuga() { return posicionTortuga; }
    public static int getPosicionLiebre() { return posicionLiebre; }

    public static void actualizarPosicion(String animal, int nuevaPosicion) {
        if (animal.equals("T")) {
            posicionTortuga = Math.max(1, nuevaPosicion);
        } else if (animal.equals("L")) {
            posicionLiebre = Math.max(1, nuevaPosicion);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("COMIENZA LA CARRERA ENTRE LA TORTUGA Y LA LIEBRE CUESTA ARRIBA.");
        System.out.println("Pista: 70 casillas. Inicio: 1. Meta: 70.");

        Animal tortuga = new Animal("Tortuga", "T");
        Animal liebre = new Animal("Liebre", "L");

        Thread hiloTortuga = new Thread(tortuga);
        Thread hiloLiebre = new Thread(liebre);

        hiloTortuga.start();
        hiloLiebre.start();

        // USO DE join()
        hiloTortuga.join();
        hiloLiebre.join();

        System.out.println("\n*** RESULTADO FINAL ***");
        determinarGanador();
    }

    public static void comprobarYTerminar() {
        // USO DE synchronized
        synchronized (LOCK) {
            if ((posicionTortuga >= META || posicionLiebre >= META) && !carreraTerminada) {
                carreraTerminada = true;
                // USO DE notifyAll()
                LOCK.notifyAll();
            }
        }
    }

    public static void determinarGanador() {
        boolean tortugaGana = posicionTortuga >= META;
        boolean liebreGana = posicionLiebre >= META;

        System.out.println("Posición final Tortuga (T): " + posicionTortuga);
        System.out.println("Posición final Liebre (L): " + posicionLiebre);

        if (tortugaGana && liebreGana) {
            System.out.println("EMPATE! Ambos llegaron a la meta!");
        } else if (tortugaGana) {
            System.out.println("LA TORTUGA GANA LA CARRERA!");
        } else if (liebreGana) {
            System.out.println("LA LIEBRE GANA LA CARRERA!");
        } else {
            System.out.println("Carrera no completada.");
        }
    }

    public static class TurnoControl {
        private boolean turnoTortuga = true;

        public synchronized void esperarTurno(String animal) throws InterruptedException {
            // USO DE synchronized y wait()
            while ((animal.equals("T") && !turnoTortuga) || (animal.equals("L") && turnoTortuga)) {
                wait();
            }
        }

        public synchronized void cambiarTurno() {
            // USO DE synchronized y notifyAll()
            turnoTortuga = !turnoTortuga;
            notifyAll();
        }
    }
}