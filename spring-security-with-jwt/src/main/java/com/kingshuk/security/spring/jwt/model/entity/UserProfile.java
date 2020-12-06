package com.kingshuk.security.spring.jwt.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER_PROFILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
public class UserProfile {

    @Id
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    private boolean isActive;


    @Column(name = "FIRST_NAME")
    private String firstName;


    @Column(name = "LAST_NAME")
    private String lastName;


    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UserRole> roles;

}
