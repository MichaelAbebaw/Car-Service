package com.cars.demo.carservice.entity;

import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserAccount() {}

    public UserAccount(String username, String password, String ... auth) {
        this.username = username;
        this.password = password;
        this.authorities = Arrays.stream(auth)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }

    public UserDetails asUser() {
        return User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .authorities(authorities)
                .build();
    }
}
