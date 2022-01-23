package com.malli.Registraion.bindings;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class User {
	

	
	Integer userId ;
	String userFirstName;
	String userLastName;
	String userEmail;
	String userPhno;
	Date userDob;
	String userGender;
	String userCountry;
	String userState ;
	String userCity ;
	String userPwd ;
	String userAccStatus ;
	LocalDate createdDate ;
	LocalDate updatedDate ;
	

	public User(String userFirstName, String userEmail) {
		super();
		this.userFirstName = userFirstName;
		this.userEmail = userEmail;
	}


	public User() {
		super();
	}
	
}
