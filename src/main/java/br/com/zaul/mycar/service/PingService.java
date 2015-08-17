package br.com.zaul.mycar.service;

import java.net.URL;

import javax.ejb.Stateless;

@Stateless
public class PingService {

	public void pingServer() {
		try {
			URL url = new URL("http://mycar-fonseca.rhcloud.com");
			url.getContent();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
