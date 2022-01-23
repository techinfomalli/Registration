package com.malli.Registraion.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="COUNTRY_MASTER")
public class Country {
	
	

	public Country(Integer countryId, String countryName) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
	}

	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	Integer countryId;	
	
	@Column(name="COUNTRY_NAME")
	String countryName;

}
