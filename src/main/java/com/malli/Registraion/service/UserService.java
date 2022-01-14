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
import com.malli.Registraion.exception.InternalServerError;
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
		User user = new User();
		//usreEntity.stream().forEach(entity-> BeanUtils.copyProperties(entity, user) ).collect());
		users.add(user);
		
		for (UserEntity entity : usreEntity) {
			
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		logger.info("***End of started***");
		return users;

	}

	public String deleteUser(Integer userId) {
		logger.info("***User deletion started***");
		logger.info("userId {}", userId);
		userRepo.deleteById(userId);
		logger.info("***User deletion compleated***");
		return "Deleted successfully.";

	}

	public String createUser(User user) throws Exception {
		logger.info("***User creation started***");
		logger.info("User {}", user);
		
		try {
			UserEntity userEntity = new UserEntity();
			userEntity = userRepo.findByUserEmail(user.getUserEmail());
			if(userEntity.getUserId()!=null) {
				return "User Already exists";
			}
		} catch (NullPointerException e) {
			//throw new Exception(e.getLocalizedMessage());
		}
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		entity.setUserAccStatus("LOCKED");
		entity.setUserPwd(generateTempPwd());
		UserEntity userEntity = userRepo.save(entity);
		if (userEntity.getUserId() != null) {
			// emaiUtils.sendEmail("Subject","body",userEntity.getUserEmail());
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

	public String unlockAccount(UnlockAccount unlockAccount) throws Exception {

		UserEntity userEntity = userRepo.findByUserEmail(unlockAccount.getEmail());
		String Status = "No msg";
		logger.info("***Started unlock user account");
		logger.info("input unlockAccount:{}",unlockAccount);
		logger.info("from database: userEntity:{}",userEntity);
		try {
			if (userEntity.getUserPwd().equals(unlockAccount.getTemppwd())) {
				{
					userEntity.setUserAccStatus("ACTIVE");
					userRepo.save(userEntity);
					Status = "Account is activated";
					emaiUtils.sendEmail(Status, readMailBodyunlockAccount(userEntity), userEntity.getUserEmail());
				}

			} else {
				Status = "Account credientials not matched.";

			}
		} catch (Exception e) {
			logger.info("Exception occered while unlockAccount:{}",e);
			throw new InternalServerError("Internal server error.");
		}
		return Status;

	}

	public String readMailBodyunlockAccount(UserEntity userEntity) throws Exception {
		String mailBody = "something";
		StringBuffer buffer = new StringBuffer();
		String file = "UNLOCK-ACCOUNT.txt";
		Path path = Paths.get(file);
		System.out.println(path);
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line -> {
				buffer.append(line);
			});

			mailBody = buffer.toString();
			mailBody = mailBody.replace("FNAME", userEntity.getUserFirstName());

		} catch (IOException e) {
			throw new InternalServerError("Error in mail server");
		}

		return mailBody;

	}

//login
////////////////////////////////////////////////////////////////////////////////////////////
	public String login(LoginAccount loginAccount) throws Exception {
		UserEntity userEntity = null;
		logger.info("loginAccount {}", loginAccount);
		try {
			userEntity = userRepo.findByUserEmail(loginAccount.getUserEmail());

		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		logger.info("userEntity {}", userEntity);
		if (userEntity == null) {
			return loginAccount.getUserEmail() + " don't have account.";

		}

		if (userEntity.getUserAccStatus().equals("LOCKED")) {
			return "Your account is locked.";
		}
		if (userEntity.getUserPwd().equals(loginAccount.getUserPwd())) {

			return "Login Success";
		}else {
			return "Login credientials are not matched.";
		}

		

	}

}