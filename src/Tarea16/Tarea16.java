package Tarea16;

    class Contador {
        private int valor = 0;
        //Ahora solo un hilo se puede sincronizar a la vez

        public synchronized void incrementar() { valor++; }
        public int obtenerValor() { return valor; }
    }

    class HiloContador extends Thread {
        private Contador contador;
        public HiloContador(Contador c) { this.contador = c; }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                contador.incrementar();
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        }
    }

    public class Tarea16 {
        public static void main(String[] args) throws InterruptedException {
            Contador contadorCompartido = new Contador();

            Thread h1 = new HiloContador(contadorCompartido);
            Thread h2 = new HiloContador(contadorCompartido);
            Thread h3 = new HiloContador(contadorCompartido);
            Thread h4 = new HiloContador(contadorCompartido);
            Thread h5 = new HiloContador(contadorCompartido);

            h1.start(); h2.start(); h3.start(); h4.start(); h5.start();


            h1.join(); h2.join(); h3.join(); h4.join(); h5.join();

            System.out.println("Valor final del contador: " + contadorCompartido.obtenerValor());
        }
    }
