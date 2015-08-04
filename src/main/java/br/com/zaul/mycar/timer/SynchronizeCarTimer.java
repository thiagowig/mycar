package br.com.zaul.mycar.timer;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.zaul.mycar.service.CarService;

@Stateless
public class SynchronizeCarTimer {
	
	@Inject
	private CarService carService;

	@Schedule(second="*", minute="1",hour="*", persistent=false)
	public void findNewCars() {
		//carService.synchronizeCars();
	}
}
