package com.malli.Registraion.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CITY_MASTER")
public class City {
	
	
	@Id
	@GeneratedValue
	@Column(name="CITY_ID")
	Integer cityId;
	
	@Column(name="CITY_NAME")
	String CityName;
	
	@Column(name="STATE_ID")
	String stateId	;

}
