package Tarea36;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EnviadorSmtp {

    private final Session session;

    public EnviadorSmtp(Session session) {
        this.session = session;
    }

    /**
     * Envía un correo electrónico de notificación usando SMTP.
     */
    public void enviarNotificacion(String destinatario, String tuNombre) {
        System.out.println("----- INICIANDO ENVÍO (SMTP) -----");
        try {
            //creo el mensaje con (MimeMessage)
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ConfiguracionCorreo.EMAIL_REMITENTE));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            //Mensajes de confirmacion
            message.setSubject("Prueba de Agente - " + tuNombre);
            message.setText("El sistema de notificaciones está activo.");

            //envio el mensaje con (Transport)
            Transport.send(message);

            System.out.println("Mensaje enviado con éxito a: " + destinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}