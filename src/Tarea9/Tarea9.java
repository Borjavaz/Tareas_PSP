package Tarea9;

import java.util.Scanner;

public class Tarea9 extends Thread{
    private int num;

    public Tarea9(int n) {
        this.num = n;
    }

    @Override
    public void run() {
        System.out.print("Fibonacci: ");
        for (int i = 1; i <= num; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    public static int fibonacci(int i) {
        if (i == 0) {
            return 0;
        } else if (i == 1){
            return 1;
        } else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce cuantos números de la sucesion de Fibonacci quiere: ");
        int n = sc.nextInt();

        new Tarea9(n).start();
    }
}