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
	
	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	Integer countryId;	
	
	@Column(name="COUNTRY_NAME")
	String countryName;

}
