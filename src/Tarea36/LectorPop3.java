package Tarea36;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;

public class LectorPop3 {

    private final Session session;
    private final ConfiguracionCorreo config;

    public LectorPop3(Session session, ConfiguracionCorreo config) {
        this.session = session;
        this.config = config;
    }

    /**
     * Conecta, accede al INBOX y lee los mensajes (POP3).
     */
    public void leerBandejaEntrada() {
        System.out.println("\n----- INICIANDO LECTURA (POP3) -----");
        Store almacen = null;
        Folder bandejaEntrada = null;

        try {
            //Me conecto al almacen con(Store)
            almacen = session.getStore("pop3");
            almacen.connect(config.getPop3Host(), config.getNombreUsuario(), config.getContrasena());
            System.out.println("Conectado al servidor POP3.");

            //Abro la carpeta - INBOX
            bandejaEntrada = almacen.getFolder("INBOX");
            bandejaEntrada.open(Folder.READ_ONLY);
            System.out.println("Abriendo la carpeta INBOX (Total mensajes: " + bandejaEntrada.getMessageCount() + ")");

            //leo y muestro los mensajes
            Message[] mensajes = bandejaEntrada.getMessages();

            //muestro los 4 mensajes más recientes
            int count = Math.min(mensajes.length, 4);
            System.out.println("\nMostrando los " + count + " correos más recientes:");

            //Recorro desde atras a delante para obtener los más recientes
            for (int i = mensajes.length - count; i < mensajes.length; i++) {
                Message mensaje = mensajes[i];
                String remitente = InternetAddress.toString(mensaje.getFrom());

                //muestro el remitente, asunto y contenido
                System.out.println("\n" + "-".repeat(40));
                System.out.println("Remitente: " + remitente);
                System.out.println("Asunto: " + mensaje.getSubject());

                // LEER EL CONTENIDO - NUEVO
                try {
                    String contenido = mensaje.getContent().toString();
                    System.out.println("Contenido: " + contenido);
                } catch (Exception e) {
                    // Si falla, mostrar el tipo de contenido
                    System.out.println("Contenido: [" + mensaje.getContentType() + "]");
                }
            }
            System.out.println("\n" + "-".repeat(40));

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al leer el correo: " + e.getMessage());
        } finally {
            //cierro la bandeja de entrada y el almacen
            try {
                if (bandejaEntrada != null && bandejaEntrada.isOpen()) {
                    bandejaEntrada.close(false);
                }
                if (almacen != null && almacen.isConnected()) {
                    almacen.close();
                }
            } catch (MessagingException ignored) {}
        }
    }
}