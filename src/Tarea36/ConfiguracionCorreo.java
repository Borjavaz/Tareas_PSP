package Tarea36;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import java.util.Properties;

public class ConfiguracionCorreo {

    //Credenciales y Servidores
    private static final String NOMBRE_USUARIO = "7a543b7614dbdd"; // Reemplazar
    private static final String CONTRASENA = "1cbfe242a3f57a"; // Reemplazar
    public static final String EMAIL_REMITENTE = "bdesaavazquez@danielcastelao.org";

    private static final String SMTP_HOST = "sandbox.smtp.mailtrap.io";
    private static final String SMTP_PORT = "2525";
    private static final String IMAP_HOST = "sandbox.imap.mailtrap.io";
    private static final String IMAP_PORT = "993";

    /**
     * Crea y configura la sesión de correo.
     * @return Sesión de Jakarta Mail.
     */
    public Session getSession() {
        Properties props = new Properties();

        //Envío (SMTP)
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //Recepción (IMAP)
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.host", IMAP_HOST);
        props.put("mail.imap.port", IMAP_PORT);
        props.put("mail.imap.ssl.enable", "true");

        //devuelve la sesión
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(NOMBRE_USUARIO, CONTRASENA);
            }
        });
    }

    // acceso para el lectorIMAP
    public String getNombreUsuario() { return NOMBRE_USUARIO; }
    public String getContrasena() { return CONTRASENA; }
    public String getImapHost() { return IMAP_HOST; }
}