package com.kingshuk.security.spring.jwt.model;


import com.kingshuk.security.spring.jwt.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserProfile, String> {

    Optional<UserProfile> findByUserName(String userName);
}
