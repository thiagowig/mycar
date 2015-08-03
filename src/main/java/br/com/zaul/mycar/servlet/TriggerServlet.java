package br.com.zaul.mycar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/trigger")
public class TriggerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.sendMail();
		
		PrintWriter out = resp.getWriter();
		out.println("JOB Triggered");
	}

	private void sendMail() {
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

			message.setContent("<h1>Sera?</h1>", "text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
