package com.malli.Registraion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malli.Registraion.bindings.LoginAccount;
import com.malli.Registraion.models.UserEntity;
import com.malli.Registraion.repositories.UserRepository;
import com.malli.Registraion.utils.EmailUtils;

@Service
public class LoginService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	EmailUtils emaiUtils;
	
	
	
public String forgetPassword(LoginAccount userEmai) {
		
		String Status=null;
		UserEntity userEntity =userRepo.findByUserEmail(userEmai.getUserEmail());
		if(userEntity==null) {
			return "User not found with email id";
		}else {
			Status="Password has been sent to you registered mail.";
			// emaiUtils.sendEmail("Passsword",readMailBodyForgetPassword(userEntity),userEntity.getUserEmail());
				
		}
		return null;
	}
	
/*
	public String loginAccount(LoginAccount login) {
		UserEntity userEntity=null;
		String Status=null;
		System.out.println("login.getEmail():" + login.getUserEmail());
	
		List<UserEntity> userEntityList =userRepo.findAll();	//findByUserEmail(login.getUserEmail());
		System.out.println("userEntityList:" + userEntityList);
		for(UserEntity entity:userEntityList) {
			if(entity.getUserEmail().equals(login.getUserEmail())) {
				userEntity=entity;
				System.out.println("userEntity" + userEntity);
			}
		}
		System.out.println("userEntity:" + userEntity);
		if (userEntity != null && userEntity.getUserPwd() == login.getUserPwd()) {
			return "Login Success";
		}
		return "Login failed";

	}
*/
}
