package com.malli.Registraion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.malli.Registraion.exception.InternalServerError;
import com.malli.Registraion.service.UserService;



@RestController
public class UnlockAccountController {
	

	@Autowired
	UserService userService;
	
	@GetMapping("/sriramaunlock")
	public String test() {
		return "Srirama-unlock-account";
	}
	
	@GetMapping("/unlock")
	public String unlockAccount(@RequestBody com.malli.Registraion.bindings.UnlockAccount unlockAccount) throws Exception {
		
		return userService.unlockAccount(unlockAccount);
		}

}
