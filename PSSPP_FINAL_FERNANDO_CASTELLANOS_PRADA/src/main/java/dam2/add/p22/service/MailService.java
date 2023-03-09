package dam2.add.p22.service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


public class MailService {
	
	public static void enviarMail (String destinatario, String dinero, String nombreUsuario) throws AddressException, MessagingException {
				
	      String to = destinatario;
	      String from = "eurocontroltelco@gmail.com";
	      String host = "smtp.gmail.com"; 
	      
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.port", "587");
	      properties.setProperty("mail.smtp.auth", "true");
	      properties.setProperty("mail.smtp.starttls.enable", "true");

	      Session session = Session.getDefaultInstance(properties, new Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, "Euro$123");
	         }
	      });

	      MimeMessage message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(from));
	      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	      message.setSubject("Te han enviado dinero");
	      message.setText("El usuario "+nombreUsuario+" te ha enviado "+dinero+" €");

	      Transport.send(message);
	      System.out.println("Correo enviado satisfactoriamente...");
	   }
		
		
	}
	
	
	
	

