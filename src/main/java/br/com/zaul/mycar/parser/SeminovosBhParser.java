package br.com.zaul.mycar.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.zaul.mycar.entities.SearchSite;
import br.com.zaul.mycar.entities.Vehicle;

public class SeminovosBhParser {

	private SearchSite searchSite;
	
	private Document mainHtml;

	public SeminovosBhParser(SearchSite searchSite) {
		this.searchSite = searchSite;
	}
	
	public List<Vehicle> parseNewVehicles() {
		try {
			
			this.mainHtml = this.openURL(this.searchSite.getInitialParameters());
			
			return this.parseVehicles();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Document openURL(String parameters) throws IOException {
		return this.getDocument(parameters);
	}
	
	private Document getDocument(String parameters) throws IOException {
		Connection connection = Jsoup.connect(this.searchSite.getDomain() + parameters);
		return connection.get();
	}
	
	private List<Vehicle> parseVehicles() throws IOException {
		List<Vehicle> vehicles = this.iterateVehicles(this.mainHtml);
		
		Elements pagedHtmls = mainHtml.select("p[class$=paginacao]").select("a[class$=numero]");
		
		for (Element element : pagedHtmls) {
			Document pagedHtml = this.openURL(element.attr("href"));
			
			vehicles.addAll(this.iterateVehicles(pagedHtml));
		}
		
		return vehicles;
	}
	
	private List<Vehicle> iterateVehicles(Document html) {
		Map<Long, Vehicle> vehicles = new HashMap<Long, Vehicle>();
		
		Elements vehiclesElement = html.select("div[id$=topoConteudoBusca]").select("dl").select("a");
		
		for (Element vehicleElement : vehiclesElement) {
			String href = vehicleElement.attr("href");
			Long vehicleId = this.extractVehicleId(href);
			
			if (vehicleId != null ) {
				
				if (!vehicleElement.select("div[class$=veiculoVendido]").isEmpty()) {
					Vehicle vehicleSold = vehicles.get(vehicleId);
					
					if (vehicleSold != null) {
						vehicleSold.setSold(true);
					}
					
				} else if (!searchSite.getExistingVehicles().contains(vehicleId)) {
					Vehicle vehicle = generateVehicle(vehicleElement, href, vehicleId);
					
					vehicles.put(vehicleId, vehicle);
				}
			}
		}
		
		return new ArrayList<Vehicle>(vehicles.values());
	}

	private Vehicle generateVehicle(Element vehicleElement, String href, Long vehicleId) {
		Vehicle vehicle = new Vehicle();
		
		vehicle.setCode(vehicleId);
		vehicle.setHref(href);
		vehicle.setImage(vehicleElement.select("img").attr("src"));
		vehicle.setDescription(vehicleElement.select("img").attr("alt"));
		vehicle.setPrice(vehicleElement.parent().parent().select("span[class$=preco_busca]").text());
		vehicle.setSearchSite(this.searchSite);
		
		return vehicle;
	}
	
	private Long extractVehicleId(String href) {
		String[] parameters = href.split("/");
		
		if (parameters.length == 4) {
			return Long.valueOf(parameters[3]);
			
		} else {
			return null;
		}
	}
	
}
