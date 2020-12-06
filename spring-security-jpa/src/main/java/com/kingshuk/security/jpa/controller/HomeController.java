package com.kingshuk.security.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String allUserAPI(){
        return "Welcome to the spring security with jdbc project, everyone...!!";
    }

    @GetMapping("/users")
    public String usersAPI(){
        return "Welcome to the spring security with jdbc project, non-admin user...!!";
    }

    @GetMapping("/admin")
    public String adminAPI(){
        return "Welcome to the spring security with jdbc project, admin user...!!";
    }
}
