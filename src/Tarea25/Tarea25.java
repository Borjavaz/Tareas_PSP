package Tarea25;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Tarea25 {
    public static void main(String[] args) {

        Scanner scanner  = new Scanner(System.in);

        System.out.println("Escribe la direccion IP a la que quieres conectarte");
        String ip = scanner.nextLine();

        System.out.println("Escribe el puerto al que quieras conectarte: ");
        int puerto = scanner.nextInt();

        //Declaro variables para los puertos "famosos"

        int FTP = 21;
        int SSH  = 22;
        int HTTP = 80;
        int HTTPS = 443;

        try{
            if(puerto != FTP && puerto != SSH && puerto != HTTP && puerto != HTTPS) {
                //Creamos el socket con la ip y el puerto introducidos por el usuario
                Socket socket = new Socket(ip, puerto);
                System.out.println("Conectado a: " + socket.getInetAddress());
                socket.close();
            }
            if(puerto == FTP){
                Socket socket = new Socket(ip,21);
                System.out.println("Conectado a: " + socket.getInetAddress());
                socket.close();
            }
            if(puerto == SSH){
                Socket socket = new Socket(ip,22);
                System.out.println("Conectado a: " + socket.getInetAddress());
                socket.close();
            }
            if(puerto == HTTP){
                Socket socket = new Socket(ip,80);
                System.out.println("Conectado a: " + socket.getInetAddress());
                socket.close();
            }
            if(puerto == HTTPS){
                Socket socket = new Socket(ip,443);
                System.out.println("Conectado a: " + socket.getInetAddress());
                socket.close();
            }
        }catch (IOException e){
            System.out.println("Error, no se pudo conectar ");
        }
    }
}
