package com.malli.Registraion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.malli.Registraion.bindings.User;
import com.malli.Registraion.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/test1")
	public String test() {
		return "Srirama";
	}

	@GetMapping("/createUser")
	public String createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/getAllUser")
	public List<User> createUser() {
		return userService.findAllUsers();
	}

}
