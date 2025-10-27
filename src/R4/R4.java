package R4;

public class R4 {
    public static void main(String[] args) {
        Object paso1 = new Object();
        Object paso2 = new Object();

        // Creacion de los 3 hilos
        Thread departamentoRedaccion = new Thread(new DepartamentoRedaccion(paso1));
        Thread departamentoLegal = new Thread(new DepartamentoLegal(paso1, paso2));
        Thread departamentoFinal = new Thread(new DepartamentoFinal(paso2));

        //Lanzar los 3 hilos
        departamentoRedaccion.start();
        departamentoLegal.start();
        departamentoFinal.start();

        try {
            departamentoFinal.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    class DepartamentoRedaccion implements Runnable {
        private final Object paso1;
        public DepartamentoRedaccion(Object paso1) {
            this.paso1 = paso1;
        }
        @Override
        public void run() {
            try {
                System.out.println("Departamento Redaccion: Empezando a redactar documento");
                Thread.sleep(3000);
                System.out.println("Departamento Redaccion: Terminada la redaccion");
                synchronized (paso1) {
                    paso1.notify(); //Notificar al Dpartamento Legal
                }
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

    }
    class DepartamentoLegal implements Runnable {
        private final Object paso1;
        private final Object paso2;

        public DepartamentoLegal(Object paso1, Object paso2) {
            this.paso1 = paso1;
            this.paso2 = paso2;
        }

        @Override
        public void run() {
            try {
                synchronized (paso1) {
                    paso1.wait();
                }
                System.out.println("Departamento Legal: Empezando la revision");
                Thread.sleep(2000);
                System.out.println("Departamento Legal: Terminada la revision");
                synchronized (paso2) {
                    paso2.notify();
                }
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
    class DepartamentoFinal implements Runnable {

        private final Object paso2;

        public DepartamentoFinal(Object paso2) {
            this.paso2 = paso2;
        }

        @Override
        public void run() {
            try {
                synchronized (paso2) {
                    paso2.wait();
                }
                System.out.println("Departamento Final: Empezando el envio");
                Thread.sleep(1000);
                System.out.println("Departamento Final: Envio terminado");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

