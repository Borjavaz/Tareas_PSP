package Tarea24;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Tarea24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca un dominio para conocer su IP: ");
        String dominio = scanner.nextLine();

        try{
            InetAddress ip = InetAddress.getByName(dominio);
            System.out.println("La IP del dominio es: " + ip.getHostAddress());

            InetAddress local = InetAddress.getLocalHost();
            System.out.println("La direccion ip de tu propia maquina es: " + local.getHostAddress());

        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
