package com.malli.Registraion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		logger.info("***User created started***");
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
}