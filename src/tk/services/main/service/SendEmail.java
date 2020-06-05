/**
 * 
 */
package tk.services.main.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

public class SendEmail extends HttpServlet {
    public static void sendMail(String host,String port,String user,String pass,String recv,String subject,String content) throws AddressException, MessagingException {
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        };
        Session session = Session.getInstance(properties, auth);
        Message mesg = new MimeMessage(session);
        mesg.setFrom(new InternetAddress(user));
        InternetAddress[] toAddresses = { new InternetAddress(recv) };
        mesg.setRecipients(Message.RecipientType.TO, toAddresses);
        mesg.setSubject(subject);
        mesg.setSentDate(new Date());
        mesg.setText(content);
        Transport.send(mesg);
 
    }
}
