package com.malli.Registraion.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malli.Registraion.bindings.Forgetpwd;
import com.malli.Registraion.models.UserEntity;
import com.malli.Registraion.repositories.UserRepository;
import com.malli.Registraion.utils.EmailUtils;

@Service
public class ForgetPasswordService {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	EmailUtils emaiUtils;
	
	public String forgetPassword(Forgetpwd forgetpwd) throws Exception {
		
		String Status=null;
		UserEntity userEntity =userRepo.findByUserEmail(forgetpwd.getUserEmail());
		if(userEntity==null) {
			return "User not found with email id";
		}else {
			Status="Password has been sent to you registered mail.";
			 emaiUtils.sendEmail("Passsword",readMailBodyForgetPassword(userEntity),userEntity.getUserEmail());
				
		}
		return Status;
	}
	
	
	
	

public String readMailBodyForgetPassword(UserEntity userEntity) {
	String mailBody="something";
	StringBuffer buffer = new StringBuffer();
	String file="FORGET-PASSWORD.txt";
	Path path =Paths.get(file);
	System.out.println(path);
	try(
			Stream<String> stream=Files.lines(path)){
		stream.forEach(line ->{
			buffer.append(line);
		});
	
		mailBody=buffer.toString();
		mailBody=mailBody.replace("FNAME", userEntity.getUserFirstName());
		mailBody=mailBody.replace("PASSWORD", userEntity.getUserPwd());
	
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


return mailBody;
	

}

}
