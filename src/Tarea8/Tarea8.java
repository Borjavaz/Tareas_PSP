package Tarea8;

public class Tarea8 extends Thread {

    private int nivelCabreo;

    /**
     * Constructor que recibe el nombre del hilo y lo pasa
     * a la clase padre (Thread) para su inicialización.
     * @param nombreProfesor El nombre para este hilo.
     */
    public Tarea8(String nombreProfesor, int nivelCabreo) {
        super(nombreProfesor);
        this.nivelCabreo = nivelCabreo;
    }

    // Definimos lo que hace el hilo
    @Override
    public void run() {
        for (int i = 0; i <= nivelCabreo; i++) {
            if(nivelCabreo == i) {
                System.out.println("[" + getName() + "]" + " Cabreo nivel: " + nivelCabreo + " ...¡He llegado a mi limite!");

            }else {
                System.out.println("[" + getName() + "]" + " Cabreo nivel: " + i);

            }
        }
    }

    /**
     * El método main es el punto de entrada.
     */
    public static void main(String[] args) {
        new Tarea8("Diego",3).start();
        new Tarea8("Manuel",3).start();
        new Tarea8("Damian",4).start();
        new Tarea8("Araujo",2).start();

        System.out.println("Programa principal terminado");
    }
}