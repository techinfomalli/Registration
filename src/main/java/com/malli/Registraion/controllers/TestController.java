package com.malli.Registraion.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/test1")
	public String testexception() throws Exception {
		throw new Exception("you are fool");
	}

}
