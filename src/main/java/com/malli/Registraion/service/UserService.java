package com.malli.Registraion.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malli.Registraion.bindings.LoginAccount;
import com.malli.Registraion.bindings.UnlockAccount;
import com.malli.Registraion.bindings.User;
import com.malli.Registraion.models.UserEntity;
import com.malli.Registraion.repositories.UserRepository;
import com.malli.Registraion.utils.EmailUtils;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	EmailUtils emaiUtils;
	
	 Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public List<User> findAllUsers() {
		logger.info("***findAllUsers started***");
		List<User> users = new ArrayList();
		List<UserEntity> usreEntity = userRepo.findAll();
		for (UserEntity entity : usreEntity) {
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		logger.info("***End of started***");
		return users;

	}

	public String deleteUser(Integer userId) {
		logger.info("***User deletion started***");
		logger.info("userId {}",userId);
		userRepo.deleteById(userId);
		logger.info("***User deletion compleated***");
		return "Deleted successfully.";

	}

	public String createUser(User user) {
		logger.info("***User creation started***");
		logger.info("User {}",user);
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		entity.setUserAccStatus("LOCKED");
		entity.setUserPwd(generateTempPwd());
		UserEntity userEntity=userRepo.save(entity);
		if(userEntity.getUserId()!=null) {
			emaiUtils.sendEmail("Subject","body",userEntity.getUserEmail());
		}
		logger.info("***End ofcreateUser ***");
		return "User created successfully.";

	}

	private String generateTempPwd() {
		logger.info("*entered to generateTempPwd");
		String tempPwd = null;
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

		tempPwd = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		logger.info("*End generateTempPwd");
		return tempPwd;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
public String unlockAccount(UnlockAccount unlockAccount) {
		
		UserEntity userEntity =userRepo.findByUserEmail(unlockAccount.getEmail());
		String Status;
		
		 if (userEntity!=null) {
			 Status= "Account not found";
		 }
		 if (userEntity.getUserAccStatus()=="LOCKED") {
			 
			 userEntity.setUserAccStatus("ACTIVE");
			 userRepo.save(userEntity);
			 Status= "Account is activated";
			 emaiUtils.sendEmail(Status,readMailBodyunlockAccount(userEntity),userEntity.getUserEmail());
		
		
		
	}else {
		Status= "Account not locked.";
		
	}
		return Status;
	
}

public String readMailBodyunlockAccount(UserEntity userEntity) {
	String mailBody="something";
	StringBuffer buffer = new StringBuffer();
	String file="UNLOCK-ACCOUNT.txt";
	Path path =Paths.get(file);
	System.out.println(path);
	try(
			Stream<String> stream=Files.lines(path)){
		stream.forEach(line ->{
			buffer.append(line);
		});
	
		mailBody=buffer.toString();
		mailBody=mailBody.replace("FNAME", userEntity.getUserFirstName());
	
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


return mailBody;
	

}

//login
////////////////////////////////////////////////////////////////////////////////////////////
public String login(LoginAccount loginAccount) {
	
	logger.info("loginAccount {}",loginAccount);
	UserEntity userEntity =userRepo.findByUserEmail(loginAccount.getUserEmail());
	logger.info("userEntity {}",userEntity);
	if( userEntity.getUserPwd().equals(loginAccount.getUserPwd())) {
		return "Login Success";
	}

	return "Login Failed";

}

}