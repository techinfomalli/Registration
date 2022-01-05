package com.malli.Registraion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public List<User> findAllUsers() {

		List<User> users = new ArrayList();
		List<UserEntity> usreEntity = userRepo.findAll();
		for (UserEntity entity : usreEntity) {
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}

		return users;

	}

	public String deleteUser(Integer userId) {
		userRepo.deleteById(userId);
		return "Deleted successfully.";

	}

	public String createUser(User user) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		entity.setUserPwd(generateTempPwd());
		UserEntity userEntity=userRepo.save(entity);
		if(userEntity.getUserId()!=null) {
			emaiUtils.sendEmail("Subject","body",userEntity.getUserEmail());
		}

		return "User created successfully.";

	}

	private String generateTempPwd() {
		String tempPwd = null;
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

		tempPwd = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return tempPwd;
	}
}