package br.com.zaul.mycar.timer;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import br.com.zaul.mycar.email.EmailSender;
import br.com.zaul.mycar.service.PingService;

@Stateless
public class PingTimer { 
	
	@EJB
	private PingService pingService;
	
	@Schedule(second="*", minute="*",hour="*/6", persistent=false)
	public void pingServer() {
		pingService.pingServer();
		new EmailSender().send("Ping server: " + new Date().toString(), "PING!");
	}

}
