package com.kingshuk.security.jpa.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @Id
    @Column(name = "USER_NAME")
    private String userName;

    @MapsId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME")
    private UserProfile profile;

    @Column(name = "AUTHORITY")
    private String role;
}
