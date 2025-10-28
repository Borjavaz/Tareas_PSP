package Carrera;

import java.util.Random;

class Animal implements Runnable {
    private final String nombre;
    private final String marcador;
    private static final int META = 70;
    private final Random random = new Random();

    private static final Carrera.TurnoControl turnoControl = new Carrera.TurnoControl();

    public Animal(String nombre, String marcador) {
        this.nombre = nombre;
        this.marcador = marcador;
    }

    @Override
    public void run() {
        while (!Carrera.carreraTerminada) {
            try {
                turnoControl.esperarTurno(marcador);

                Thread.sleep(1000);

                int movimiento = calcularMovimiento();
                int posicionActual = (marcador.equals("T")) ? Carrera.getPosicionTortuga() : Carrera.getPosicionLiebre();
                int nuevaPosicion = posicionActual + movimiento;

                Carrera.actualizarPosicion(marcador, nuevaPosicion);

                posicionActual = (marcador.equals("T")) ? Carrera.getPosicionTortuga() : Carrera.getPosicionLiebre();
                System.out.println("Paso " + (marcador.equals("T") ? "TORTUGA" : "LIEBRE") +
                        ": Posición " + posicionActual + " " + marcador + " (" + (movimiento > 0 ? "+" : "") + movimiento + ")");

                if (posicionActual >= META) {
                    Carrera.comprobarYTerminar();
                    break;
                }

                turnoControl.cambiarTurno();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private int calcularMovimiento() {
        int probabilidad = random.nextInt(100) + 1;
        int movimiento = 0;

        if (marcador.equals("T")) { // Tortuga
            if (probabilidad <= 50) { // Avance rápido (50%)
                movimiento = 3;
            } else if (probabilidad <= 70) { // Resbalón (20%)
                movimiento = -6;
            } else { // Avance lento (30%)
                movimiento = 1;
            }
        } else if (marcador.equals("L")) { // Liebre
            if (probabilidad <= 20) { // Duerme (20%)
                movimiento = 0;
            } else if (probabilidad <= 40) { // Gran salto (20%)
                movimiento = 9;
            } else if (probabilidad <= 50) { // Resbalón grande (10%)
                movimiento = -12;
            } else if (probabilidad <= 80) { // Pequeño salto (30%)
                movimiento = 1;
            } else { // Resbalón pequeño (20%)
                movimiento = -2;
            }
        }
        return movimiento;
    }
}