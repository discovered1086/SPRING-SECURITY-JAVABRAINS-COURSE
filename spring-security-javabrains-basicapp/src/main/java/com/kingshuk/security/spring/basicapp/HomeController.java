package com.kingshuk.security.spring.basicapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String sayHello() {
		return "Welcome to Spring Security with Java brains, Kingshuk";
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "Welcome to the application, user...!!";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "Welcome to the application, admin...!!";
	}

}
