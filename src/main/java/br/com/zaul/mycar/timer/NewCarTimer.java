package br.com.zaul.mycar.timer;

import java.util.Properties;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class NewCarTimer {

	@Schedule(second="30")
	public void findNewCars() {
		
	}
}
