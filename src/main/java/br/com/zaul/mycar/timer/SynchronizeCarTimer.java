package br.com.zaul.mycar.timer;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import br.com.zaul.mycar.service.CarService;

@Stateless
public class SynchronizeCarTimer {
	
	@EJB
	private CarService carService;

	@Schedule(second="*", minute="0,15,30,45",hour="*", persistent=false)
	public void findNewCars() {
		carService.synchronizeCars();
	}
}
