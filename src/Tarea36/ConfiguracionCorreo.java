package Tarea36;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import java.util.Properties;

public class ConfiguracionCorreo {

    //Credenciales y Servidores
    private static final String NOMBRE_USUARIO = "7a543b7614dbdd";
    private static final String CONTRASENA = "1cbfe242a3f57a";
    public static final String EMAIL_REMITENTE = "bdesaavazquez@danielcastelao.org";

    private static final String SMTP_HOST = "sandbox.smtp.mailtrap.io";
    private static final String SMTP_PORT = "2525";
    private static final String POP3_HOST = "pop3.mailtrap.io";
    private static final String POP3_PORT = "1100";

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

        //Recepción (POP3)
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.host", POP3_HOST);
        props.put("mail.pop3.port", POP3_PORT);
        props.put("mail.pop3.ssl.enable", "false");

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
    public String getPop3Host() { return POP3_HOST; }
}