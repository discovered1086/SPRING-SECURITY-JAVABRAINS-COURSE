package com.kingshuk.security.spring.jwt.service;


import com.kingshuk.security.spring.jwt.model.MyUserDetails;
import com.kingshuk.security.spring.jwt.model.UserDetailsRepository;
import com.kingshuk.security.spring.jwt.model.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("myUserDetailsService")
@AllArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile profile = userDetailsRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User with the user name not found...."));

        log.info("The user profile: " + profile);

        MyUserDetails myUserDetails = MyUserDetails.builder()
                .userName(profile.getUserName())
                .active(profile.isActive())
                .password(profile.getPassword())
                .build();

        myUserDetails.setRoles(profile.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toSet()));

        return myUserDetails;
    }
}
