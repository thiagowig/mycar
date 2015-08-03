package br.com.zaul.mycar.timer;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class NewCarTimer {

	@Schedule(second="30")
	public void findNewCars() {
		System.out.println("FOI!!!");
	}
}
