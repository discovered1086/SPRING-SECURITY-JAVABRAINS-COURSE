package com.kingshuk.security.spring.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello user....";
    }
}
