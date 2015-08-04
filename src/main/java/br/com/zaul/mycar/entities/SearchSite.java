package br.com.zaul.mycar.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table
public class SearchSite implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private String domain;
	
	@Column(unique=true)
	private String initialParameters;
	
	@Transient
	private List<Long> existingVehicles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getInitialParameters() {
		return initialParameters;
	}

	public void setInitialParameters(String initialParameters) {
		this.initialParameters = initialParameters;
	}

	public List<Long> getExistingVehicles() {
		return existingVehicles;
	}

	public void setExistingVehicles(List<Long> existingVehicles) {
		this.existingVehicles = existingVehicles;
	}

	
}
