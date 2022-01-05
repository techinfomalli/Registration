package com.malli.Registraion.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Entity
@Data
@Table(name="USER_DTLS")
public class UserEntity {
	
	@Id
	@GeneratedValue
	@Column(name="USERID")
	Integer userId ;
	@Column(name="USER_FIRST_NAME")
	String userFirstName;
	@Column(name="USER_LAST_NAME")
	String userLastName;
	@Column(name="USER_EMAIL")
	String userEmail;
	@Column(name="USER_PHNO")
	String userPhno;
	@Column(name="USER_DOB")
	Date userDob;
	@Column(name="USER_GENDER")
	String userGender;
	@Column(name="USER_COUNTRY")
	String userCountry;
	@Column(name="USER_STATE")
	String userState ;
	@Column(name="USER_CITY")
	String userCity ;
	@Column(name="USER_PWD")
	String userPwd ;
	@Column(name="USER_ACC_STATUS")
	String userAccStatus ;
	@CreationTimestamp
	@Column(name="CREATED_DATE")
	LocalDate createdDate ;
	@CreationTimestamp
	@Column(name="UPDATED_DATE")
	LocalDate updatedDate ;
	
}
