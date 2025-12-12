package Tarea36;

import jakarta.mail.Session;

public class AgenteCorreoApp {

    public static void main(String[] args) {

        String tuNombre = "Borja de Saá Vázquez";
        String destinatario = ConfiguracionCorreo.EMAIL_REMITENTE;

        //config
        ConfiguracionCorreo config = new ConfiguracionCorreo();
        Session mailSession = config.getSession();

        // inicio el servicio
        EnviadorSmtp enviador = new EnviadorSmtp(mailSession);
        LectorImap lector = new LectorImap(mailSession, config);

        // PARTE DEL AGENTE

        //Envío (SMTP)
        enviador.enviarNotificacion(destinatario, tuNombre);

        // Espero 2 seg para que el correo llegue al server
        try {
            System.out.println("Esperando 2 segundos para asegurar la llegada del email...");
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        //Lectura (IMAP)
        lector.leerBandejaEntrada();
    }
}