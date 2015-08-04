package br.com.zaul.mycar.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.zaul.mycar.entities.Vehicle;

public class EmailSender {
 
	public void send(List<Vehicle> newVehicles) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("thiagowigmycar","cru1095irib");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("thiagowigmycar@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dev.thiago@gmail.com"));
			message.setSubject("Novos veículos cadastrados");

			message.setContent(this.generateEmailContent(newVehicles), "text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private String generateEmailContent(List<Vehicle> newVehicles) {
		StringBuilder email = new StringBuilder();

		email.append("<h1>Novos Veículos</h1></br>");
		email.append("<table>");

		for (Vehicle vehicle : newVehicles) {
			
			email.append("<tr>");
			email.append("<td>");
			email.append("<img src=\"" + vehicle.getImage() + "\" />");
			email.append("</td>");
			email.append("<td>");
			email.append("<a href=\"" + vehicle.getSearchSite().getDomain() + vehicle.getHref() + "\">" + vehicle.getDescription() + "</a>");
			email.append("</td>");
			email.append("<td>");
			email.append("<span>" + vehicle.getPrice() + "</span>");
			email.append("</td>");
			email.append("</tr>");
		}

		email.append("</table>");

		return email.toString();
	}
}
