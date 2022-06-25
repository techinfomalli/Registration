package com.malli.Registraion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.malli.Registraion.bindings.LoginAccount;
import com.malli.Registraion.bindings.User;
import com.malli.Registraion.models.Country;
import com.malli.Registraion.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/createUser")
	public String createUser(@RequestBody User user) throws Exception {
		return userService.createUser(user);
	}

	
	@GetMapping("/getAllUser")
	public List<User> createUser() {
		return userService.findAllUsers();
	}

	@GetMapping("/login")
	public String login(@RequestBody LoginAccount login) throws Exception {
		return userService.login(login);
	}

	@GetMapping("/deleteWithEmil/{emil}")
	public String delete(@PathVariable String emil) throws Exception {
		return userService.delete(emil);
	}

	// @GetMapping("/delete/{id}")
	@RequestMapping(path = "/deleteWithId/{id}", method = RequestMethod.GET)
	public String deletewithId(@PathVariable String id) throws Exception {
		return userService.deleteUser(id);
	}

	@GetMapping("/emailCheckTest/{email}")
	public String emailCheckTest(@PathVariable String email) throws Throwable {
		return userService.emailCheckTest(email);

	}
	
	@GetMapping("/countryes")
	public List<Country> countryes() throws Throwable {
		return userService.getConties();

	}

}
