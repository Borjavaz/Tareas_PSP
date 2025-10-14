package Tarea19;

import java.io.IOException;
import java.util.Scanner;

public class Tarea19 {

    // Variable compartida para el total de vocales
    private static int totalVocales = 0;

    // Objeto para sincronizar el acceso a totalVocales
    private static final Object candado = new Object();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un texto:");
        String texto = sc.nextLine().toLowerCase();

        // Crear hilos
        Thread hiloA = new Thread(new ContarVocal(texto, 'a'));
        Thread hiloE = new Thread(new ContarVocal(texto, 'e'));
        Thread hiloI = new Thread(new ContarVocal(texto, 'i'));
        Thread hiloO = new Thread(new ContarVocal(texto, 'o'));
        Thread hiloU = new Thread(new ContarVocal(texto, 'u'));

        // Iniciar hilos
        hiloA.start();
        hiloE.start();
        hiloI.start();
        hiloO.start();
        hiloU.start();

        // Esperar a que terminen todos los hilos
        try {
            hiloA.join();
            hiloE.join();
            hiloI.join();
            hiloO.join();
            hiloU.join();

        }catch (InterruptedException e){
            System.out.println("Error: " + e);
        }

        // Mostrar el resultado final
        System.out.println("Número total de vocales: " + totalVocales);
    }

    // Clase interna que implementa Runnable para contar una vocal específica
    static class ContarVocal implements Runnable {
        private String texto;
        private char vocal;

        public ContarVocal(String texto, char vocal) {
            this.texto = texto;
            this.vocal = vocal;
        }

        @Override
        public void run() {
            int contador = 0;
            for (char c : texto.toCharArray()) {
                if (c == vocal) contador++;
            }

            // Sumar el resultado al total de forma sincronizada
            synchronized (candado) {
                totalVocales += contador;
            }
        }
    }
}

