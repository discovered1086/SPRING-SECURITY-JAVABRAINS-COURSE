package com.kingshuk.security.spring.jwt.service;

import com.kingshuk.security.spring.jwt.utility.JWTHelper;
import com.kingshuk.security.spring.jwt.web.model.AuthenticationRequest;
import com.kingshuk.security.spring.jwt.web.model.AuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JWTHelper jwtHelper;

    public AuthenticationResponse handleAuthentication(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (request.getUserName(), request.getPassword()));
        } catch (BadCredentialsException exception) {
            log.error("Incorrect username and/or password...", exception);
        }

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUserName());

        return new AuthenticationResponse(jwtHelper.generateToken(userDetails));

    }



}
