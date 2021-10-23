package com.bbva.pymesbbva.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Slf4j
@Component
public class EmailSender {

    private final PropertiesUtil propertiesUtil;

    public EmailSender(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
    }

    public String sendEmail(String emailReceptor, String messageBody) {
        //Mail
        Session session;
        //Conjunto de valores necesarios para la conexión
        var p = new Properties();
        //Mensaje
        MimeMessage message;

        //Nombre del host de correo, es smtp.gmail.com
        p.setProperty("mail.smtp.host", "smtp.gmail.com");
        //TLS si está disponible
        p.setProperty("mail.smtp.starttls.enable", "true");
        //Puerto de gmail para envio de correos
        p.setProperty("mail.smtp.port", "587");
        //Nombre del usuario
        p.setProperty("mail.smtp.user", propertiesUtil.EMAIL_SENDER);
        //Si requiere o no usuario y password para conectarse
        p.setProperty("mail.smtp.auth", "true");

        //Instanciamos una session
        session = Session.getDefaultInstance(p);
        //Ver errores en consola
        session.setDebug(true);

        //Instanciamos un mensaje
        message = new MimeMessage(session);

        try {
            //Quién envía el correo
            message.setFrom(new InternetAddress(propertiesUtil.EMAIL_SENDER));
            //A quién va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptor));
            //Asunto
            message.setSubject(propertiesUtil.EMAIL_SUBJECT);

            //Mensaje de texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(messageBody);

            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);

            //Mensaje a enviar
            message.setContent(multiparte);

            //Enviar el mensaje
            Transport t = session.getTransport("smtp");
            //Establecer conexion
            t.connect(propertiesUtil.EMAIL_SENDER, propertiesUtil.EMAIL_SENDER_PASSWORD);
            //Enviar
            t.sendMessage(message, message.getAllRecipients());

            //Cerrar conexion
            t.close();

            return "Sended succesfully";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }

    }

}
