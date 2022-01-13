package com.malli.Registraion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.malli.Registraion.bindings.LoginAccount;
import com.malli.Registraion.bindings.User;
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
	public String login(@RequestBody LoginAccount login) throws Exception{
		return userService.login(login);	
	}
	

}
