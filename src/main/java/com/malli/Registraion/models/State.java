package com.malli.Registraion.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="STATE_MASTER")
public class State {
	
	
	@Id
	@GeneratedValue
	@Column(name="STATE_ID")
	Integer stateId;
	@Column(name="STATE_NAME")
	String StateName;
	@Column(name="COUNTRY_ID")
	String CountryId;

}
