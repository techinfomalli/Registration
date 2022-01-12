package com.malli.Registraion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malli.Registraion.service.ForgetPasswordService;
@RestController
public class ForgetPasswordController {
	
	@Autowired
	ForgetPasswordService service;
	
	
	@GetMapping("/forgetpwd")
	public String forgetPassoword() {
		return service.forgetPassword("mallisrirama@gmail.com");

	}

}
