package br.com.zaul.mycar.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data 
public class Vehicle implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private Long code;
	
	private String href;
	
	private String image;
	
	private String description;

	private boolean sold;
	
	private boolean viewed;
	
	private String price;
	
	@ManyToOne
	private SearchSite searchSite;

}
