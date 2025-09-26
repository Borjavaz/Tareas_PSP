package Tarea3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tarea3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Pedimos al usuario que introduzca la ruta y lo guardamos como una variable
        System.out.println("Introduce la ruta o el nombre del archivo:");
        String nombreArchivo = scanner.nextLine().trim();

        //Verificamos que el usuario escribió algo
        if (nombreArchivo.isEmpty()) {
            System.out.println("Debe introducir algún nombre de archivo.");
            return;
        }

        //Creamo un nuevo objeto File llamado archivo
        File archivo = new File(nombreArchivo);

        //Comprobamos si ya existe o no
        if (archivo.exists()) {
            System.out.println("Archivo existente");
        } else {
            System.out.println("Archivo no existente. Se creará uno nuevo en: " + archivo.getAbsolutePath());
        }

        String SO = System.getProperty("os.name").toLowerCase();
        String editorTexto;

        //Comprobamos el SO y a cada uno se le asigna su editor.
        if (SO.contains("win")) {
            editorTexto = "notepad";
        } else if (SO.contains("lin")) {
            editorTexto = "gnome-text-editor";
        } else if (SO.contains("mac")) {
            editorTexto = "open";
        } else {
            System.out.println("Sistema operativo no encontrado.");
            return;
        }

        try {
            // Creamos un ProcessBuilder para ejecutar el editor con la ruta del archivo
            ProcessBuilder constructor;
            constructor = new ProcessBuilder(editorTexto, archivo.getAbsolutePath());

            //Empezadoms el proceso
            constructor.start();

        } catch (IOException e) {
            System.out.println("Fallo al intentar abrir el editor: " + e.getMessage());
        }
    }
}