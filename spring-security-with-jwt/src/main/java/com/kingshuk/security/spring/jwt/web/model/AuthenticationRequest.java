package com.kingshuk.security.spring.jwt.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    @JsonProperty("username")
    private String userName;

    private String password;
}
