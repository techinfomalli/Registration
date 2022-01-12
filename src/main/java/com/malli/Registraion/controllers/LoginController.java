package com.malli.Registraion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.malli.Registraion.bindings.LoginAccount;

@RestController
public class LoginController {
	
	@Autowired
	com.malli.Registraion.service.LoginService loginService;
	
	
	

}
