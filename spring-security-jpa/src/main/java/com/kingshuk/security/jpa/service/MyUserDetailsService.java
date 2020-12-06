package com.kingshuk.security.jpa.service;

import com.kingshuk.security.jpa.model.MyUserDetails;
import com.kingshuk.security.jpa.model.UserDetailsRepository;
import com.kingshuk.security.jpa.model.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("myUserDetailsService")
@AllArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserProfile> userProfile = userDetailsRepository.findByUserName(username);

        if (!userProfile.isPresent()) {
            throw new UsernameNotFoundException("User with the user name not found....");
        }

        UserProfile profile = userProfile.get();

        log.info("The user profile: "+userProfile);

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
