package br.com.zaul.mycar.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.zaul.mycar.email.EmailSender;
import br.com.zaul.mycar.entities.SearchSite;
import br.com.zaul.mycar.entities.Vehicle;
import br.com.zaul.mycar.parser.SeminovosBhParser;

@Stateless
public class CarService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void synchronizeCars() {
		List<SearchSite> sites = entityManager.createQuery("FROM SearchSite").getResultList();
		
		for (SearchSite searchSite : sites) {
			findNewVehicles(searchSite);
		}
	}
	
	private void findNewVehicles(SearchSite searchSite) {
		List<Long> existingVehicles = getExistingVehicles(searchSite);
		searchSite.setExistingVehicles(existingVehicles);
		
		SeminovosBhParser parser = new SeminovosBhParser(searchSite);
		List<Vehicle> newVehicles = parser.parseNewVehicles();
		
		if (newVehicles != null && !newVehicles.isEmpty()) {
			for (Vehicle vehicle : newVehicles) {
				entityManager.persist(vehicle);
			}
			
			new EmailSender().send(newVehicles);
		}
	}
	
	private List<Long> getExistingVehicles(SearchSite searchSite) {
		Query query = entityManager.createQuery("SELECT code FROM Vehicle WHERE searchSite.id = :siteId");
		query.setParameter("siteId", searchSite.getId());
		
		return query.getResultList();
	}
}
