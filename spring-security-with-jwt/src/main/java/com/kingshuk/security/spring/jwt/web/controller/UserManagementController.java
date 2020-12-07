package com.kingshuk.security.spring.jwt.web.controller;

import com.kingshuk.security.spring.jwt.service.AuthenticationService;
import com.kingshuk.security.spring.jwt.web.model.AuthenticationRequest;
import com.kingshuk.security.spring.jwt.web.model.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserManagementController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.handleAuthentication(request));
    }
}
